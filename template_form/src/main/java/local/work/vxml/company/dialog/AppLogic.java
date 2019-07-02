package local.work.vxml.company.dialog;

import local.work.vxml.base.AppGeneralException;
import local.work.vxml.base.impl.shared.BaseAppLogic;
import local.work.vxml.base.interfaces.*;
import local.work.vxml.company.dialog.vars.VARS;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Логика тестового приложения, не зависит от представления
 */
public class AppLogic extends BaseAppLogic {

    @Autowired
    private CompanyService companyService;

    private CompanyService.Order order;

    /**
     * Вызывается платформой в случае ошибки серврера или vxml
     *
     * @param dialog
     * @param result
     * @return - Диалог который воспроизводится клиенту
     * @throws AppGeneralException
     */
    @Override
    public IDialog errorHandler(IDialog dialog, IDialogResult result) throws AppGeneralException {
        // doEnd - означает больше диалогов не будет
        return doEnd(getByeDialog(VARS.BYE_PROMPTS.BYE_ERROR), DISCONNECT_REASONS.GOODEND.name());
    }

    /**
     * Стартовый диалог приложения
     *
     * @return
     * @throws AppGeneralException
     */
    @Override
    public IDialog getStartDialog() throws AppGeneralException {
        String ani = sessionService.getAsString(ISessionService.ANI_PARAM, null);
        if (ani != null) {
            order = companyService.insertPhone(ani);
            if (order != null) {
                sessionService.put(VARS.SESSION_VAR_ORDER, order);
                return lookupDialog(VARS.HELLO.NAME);
            } else {
                Logger.getLogger(getClass()).error("order not found for ani = " + ani);
            }
        } else {
            Logger.getLogger(getClass()).error("ani not defined");
        }

        IDialog dialog = lookupDialog(VARS.HELLO.NAME);
        return dialog;
    }

    /**
     * Переопределенный метод поиска следующего диалога, нужен для обработки глобальных команд
     *
     * @param currentDialog
     * @param result
     * @return
     * @throws AppGeneralException
     */
    @Override
    public IDialog getNextHandler(IDialog currentDialog, IDialogResult result) throws AppGeneralException {
        if (result.getNbest().size() > 0) {
            for (IDialogResultSlot slot : result.getNbest().get(0).getSlots()) {
                switch (slot.getValue()) {
                    case "global_repeat":
                        companyService.addAnswer("global", currentDialog.getName() + " - " + slot.getValue());
                        return lookupDialog(currentDialog.getName());
                }
            }
        }
        return notProcessed();
    }

    /**
     *
     * @param prompt
     * @return
     */
    private IDialog getByeDialog(String prompt) throws AppGeneralException {
        IDialog dialog = lookupDialog(VARS.BYE.NAME);
        dialog.getInputParams().add("prompt_set", prompt);
        return dialog;
    }

    /**
     * Приветствие, переход к следующему диалогу (can_you_speak).
     * @param result
     * @return
     * @throws AppGeneralException
     */
    public IDialog hello(IDialogResult result) throws AppGeneralException {
        if (result.getType() == IDialogResult.Type.NOINPUT) {
            companyService.addAnswer("hello", result.getType().name());
            return doEnd(lookupDialog(VARS.BYE.NAME), DISCONNECT_REASONS.NOINPUT.name());
        }
        String rule = result.getNbest().get(0).getSlot(VARS.HELLO.FIELD1.SLOTS.RULE).getValue();
        companyService.addAnswer("hello", rule);
        return lookupDialog(VARS.CAN_YOU_SPEAK.NAME);
    }

    /**
     * Диалог can_you_speak ("Удобно Вам говорить?"), переход к диалогу do_you_like ("Вам понравилось...?") .
     * @param result
     * @return
     * @throws AppGeneralException
     */
    public IDialog can_you_speak(IDialogResult result) throws AppGeneralException {
        companyService.addCallId();
        if (result.getType() == IDialogResult.Type.NOMATCH) {
            companyService.addAnswer("can_you_speak", result.getType().name());
            return doEnd(lookupDialog(VARS.BYE.NAME), result.getType().name());
        }
        String rule = result.getNbest().get(0).getSlot(VARS.CAN_YOU_SPEAK.FIELD1.SLOTS.RULE).getValue();
        companyService.addAnswer("can_you_speak", rule);
        return lookupDialog(VARS.DO_YOU_LIKE.NAME);
    }

    /**
     * Диалог do_you_like ("Вам понравилось...?"), переход к прощанию (BYE).
     * @param result
     * @return
     * @throws AppGeneralException
     */
    public IDialog do_you_like(IDialogResult result) throws AppGeneralException {
        if (result.getType() != IDialogResult.Type.MATCH) {
            companyService.addAnswer("do_you_like", result.getType().name());
            return doEnd(lookupDialog(VARS.BYE.NAME), result.getType().name());
        }
        String rule = result.getNbest().get(0).getSlot(VARS.DO_YOU_LIKE.FIELD1.SLOTS.RULE).getValue();
        companyService.addAnswer("do_you_like", rule);
        return doEnd(getByeDialog(VARS.BYE_PROMPTS.BYE), DISCONNECT_REASONS.GOODEND.name());
    }
}