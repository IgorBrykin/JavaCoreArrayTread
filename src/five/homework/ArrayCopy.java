package five.homework;

public class ArrayCopy {
    static final int size = 10000000;
    static final int h = size / 2;


    public void fillInOne() {
        float[] arr = new float[size];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }

        long a = System.currentTimeMillis();

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

        System.out.println("Время исполнения метод 1: " + (System.currentTimeMillis() - a));
    }



    public void fillInTwo() {
        float[] arr = new float[size];
        for (int i = 0; i < arr.length; i++) arr[i] = 1;
        long a = System.currentTimeMillis();
        float[] arr1 = new float[h];
        float[] arr2 = new float[h];

        copyHalf(arr, arr1, arr2);

        Thread t1 = getThread(arr1);
        Thread t2 = getThread(arr2);

        t1.start();
        t2.start();


        System.arraycopy(arr1, 0, arr, 0, h);
        System.arraycopy(arr2, 0, arr, h, h);

        System.out.println("Время исполнения метод 2:" + (System.currentTimeMillis() - a));
    }

    private void copyHalf(float[] arr, float[] arr1, float[] arr2) {
        System.arraycopy(arr, 0, arr1, 0, h);
        System.arraycopy(arr, h, arr2, 0, h);
    }

    private Thread getThread(float[] arr1) {
        return new Thread(() -> {
            for (int i = 0; i < arr1.length; i++)
                arr1[i] = (float) (arr1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        });
    }
}
