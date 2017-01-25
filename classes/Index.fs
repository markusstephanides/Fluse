// The index page of the website;
public Page Index {

    public constructor(){
        base("de");
    }

    public event onPageLoad(){
         this.add("Hello World");
         this.add(new Image("//documents/test1.jpg"));
         Console.log(Server.clientIp);
    }

    public event onPageLoaded(){
        this.add("Hello World");
        this.add(new Image("//documents/test1.jpg"));
        Console.log(Server.clientIp);
    }
}