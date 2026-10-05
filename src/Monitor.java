public class Monitor implements Runnable {
    private final Descarga[] descargas;

    public Monitor(Descarga[] descargas) {
        this.descargas = descargas;
    }

    public void run() {
        int activas = descargas.length;
        while (activas > 0) {
            activas = 0;
            for (Descarga descarga : descargas) {
                if (descarga.isAlive()) {
                    activas++;
                }
            }
            if (activas > 0) {
                System.out.println("[Monitor] Descargas en curso: " + activas);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
        System.out.println("[Monitor] No queda ninguna descarga en curso");
    }
}
