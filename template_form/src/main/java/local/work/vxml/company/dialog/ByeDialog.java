package local.work.vxml.company.dialog;

import local.work.vxml.base.AppGeneralException;
import local.work.vxml.base.impl.shared.BaseFormDialog;
import local.work.vxml.company.dialog.vars.VARS;

/**
 * Created by nesh on 14.03.2016.
 */
public class ByeDialog extends BaseFormDialog {

    public String render() throws AppGeneralException {
        String promptSet = VARS.BYE_PROMPTS.BYE;
        if (getInputParams().get("prompt_set", null) != null)
            promptSet = getInputParams().get("prompt_set");
        form.setPromptSetById(promptSet);
        return form.render();
    }

}
