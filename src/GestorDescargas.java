public class GestorDescargas {
    public static void main(String[] args) throws InterruptedException {
        String[] archivos = args;
        if (archivos.length == 0) {
            archivos = new String[] {
                "cuarzos.png", "meditacion.mp4", "mantras.mp3", "horoscopo.pdf"
            };
        }

        Descarga[] descargas = new Descarga[archivos.length];
        for (int i = 0; i < archivos.length; i++) {
            descargas[i] = new Descarga(archivos[i]);
        }

        long inicio = System.currentTimeMillis();
        for (Descarga descarga : descargas) {
            descarga.start();
        }

        Thread monitor = new Thread(new Monitor(descargas), "Monitor");
        monitor.start();

        for (Descarga descarga : descargas) {
            descarga.join();
        }
        long tiempoReal = System.currentTimeMillis() - inicio;

        monitor.join();

        long acumuladoSerie = 0;
        for (Descarga descarga : descargas) {
            acumuladoSerie += descarga.getTiempo();
        }

        System.out.println("Todas las descargas han terminado.");
        System.out.println("Tiempo real: " + tiempoReal + " ms");
        System.out.println("Si se hubieran descargado una detras de otra: " + acumuladoSerie + " ms");
    }
}
