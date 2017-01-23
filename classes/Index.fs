Page Index("de"){
    public void onPageLoaded(){
        this.add("Hello World");
        this.add(new Image("//documents/test1.jpg"));
        Console.log(Server.clientIp);
    }
}