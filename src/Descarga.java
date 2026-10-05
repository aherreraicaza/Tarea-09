public class Descarga extends Thread {
    private final String archivo;
    private final int tiempoBloque;
    private long tiempo;
    private static final int AJUSTE_BLOQUE = 1;

    public Descarga(String archivo) {
        super("Descarga-" + archivo);
        this.archivo = archivo;
        tiempoBloque = 100 + (int) (Math.random() * 401);
    }

    public void run() {
        long inicio = System.currentTimeMillis();

        for (int i = 1; i <= 10; i++) {
            try {
                Thread.sleep(tiempoBloque * AJUSTE_BLOQUE);
            } catch (InterruptedException e) {
                System.out.println("[" + archivo + "] descarga interrumpida");
                return;
            }
            System.out.println("[" + archivo + "] " + (i * 10) + "%");
        }

        tiempo = System.currentTimeMillis() - inicio;
        System.out.println("[" + archivo + "] completada en " + tiempo + " ms");
    }

    public long getTiempo() {
        return tiempo;
    }
}
