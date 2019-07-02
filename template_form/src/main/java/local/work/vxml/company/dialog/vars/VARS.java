package local.work.vxml.company.dialog.vars;

import local.work.vxml.base.vars.StandartDialogVarsVars;

public class VARS {
    // Здравствуйте, ресепшен слушает Вас
    public static final StandartDialogVarsVars HELLO = new StandartDialogVarsVars("hello", "field1");
    // Вам удобно говорить.
    public static final StandartDialogVarsVars CAN_YOU_SPEAK = new StandartDialogVarsVars("can_you_speak", "field1");
    //Вам понравилось...?
    public static final StandartDialogVarsVars DO_YOU_LIKE = new StandartDialogVarsVars("do_you_like", "field1");
    // Была рада помочь.
    public static final StandartDialogVarsVars BYE = new StandartDialogVarsVars("bye", "bye");

    public static final String SESSION_VAR_ORDER = "app_order";

    public static final String SESSION_VAR_HAVE_COMMENT = "session_var_have_comment";

    public static class BYE_PROMPTS {
        public static final String BYE = "bye";
        public static final String BYE_ERROR = "bye_error";
    }
}