/*
Dialog classes used/adapted from patrickhoey's BludBourne
Title: BludBourne
Author: patrickhoey
Source: https://github.com/patrickhoey/BludBourne
 */


package onion.szxb74om7zsmd2jm.limitlesslabyrinth.mechanics.Dialog;

/**
 * Created by 226864 on 4/18/2017.
 */
public class Conversation {
    private String id;
    private String dialog = "";

    public Conversation()
    {

    }

    public String getId() {
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getDialog(){
        return dialog;
    }

    public void setDialog(String dialog){
        this.dialog = dialog;
    }
}
