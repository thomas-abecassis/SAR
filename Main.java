package TD1;

class Main {
    public static void main(String[] args) {
        Broker client = new Broker("client");
        Broker server = new Broker("server");
        Channel serverChannel = server.accept(123); //En vrai ça bloque donc il faudrait le lancer parallélement dans un second thread mais pour simplifier l'exemple on fait comme ça
        Channel clientChannel = client.connect("server", 123);
        clientChannel.write("Hello World!".getBytes(), 0, 12);
        byte[] bytesRecus = new byte[12];
        serverChannel.read(bytesRecus,0,12);
    }
}