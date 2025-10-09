package org.example.designpattern;

class Instance
{
    private static Instance instance;
    private Instance(){}
    public static Instance getObject()
    {
        if(instance == null) {
            synchronized (Instance.class)
            {
                if(instance == null){
                    instance = new Instance();
                }
            }
        }
        return instance;
    }
}

class Billpug
{
    private Billpug(){}
    private static final class Holder
    {
        private static final Billpug billpug = new Billpug();
    }
    public static Billpug getBillpug()
    {
        return Holder.billpug;
    }
}

public class Singalton {

}
