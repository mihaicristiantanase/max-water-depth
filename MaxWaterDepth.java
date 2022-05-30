public class MaxWaterDepth {
  public static void main(String[] args) {
    expect(2, new int[] {0, 2, 0, 2});
    expect(0, new int[] {0, 2, 0});
    expect(0, new int[] {});
    expect(0, new int[] {2, -2, -3, -4});
    expect(0, new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9});
    expect(3, new int[] {2, 1, 3, 2, 0, -1, 2});
    expect(1, new int[] {2, 1, 3, 2, 0, -1, -2});
  }

  private static int getMaxWaterDepth(int[] hills) {
    int rv = 0;
    // fail fast
    if (hills == null || hills.length < 3) {
      return rv;
    }

    int n = hills.length;
    int[] lr = new int[n];
    int[] rl = new int[n];

    // scan from left to right
    lr[0] = 0;
    for (int i = 1; i < n; i++) {
      int d = hills[i] - hills[i - 1];
      lr[i] = Math.min(0, lr[i - 1] + d);
    }
    // scan right to left
    rl[n - 1] = 0;
    for (int i = n - 2; i >= 0; i--) {
      int d = hills[i] - hills[i + 1];
      rl[i] = Math.min(0, rl[i + 1] + d);
    }
    // aggregate
    for (int i = 0; i < n; i++) {
      int d = Math.max(lr[i], rl[i]);
      if (rv > d) {
        rv = d;
      }
    }
    return -rv;
  }

  private static void expect(int depth, int[] hills) {
    int d = getMaxWaterDepth(hills);
    System.out.println("Result: " +
        (depth == d ? "OK"
                    : String.format("FAIL (expected %d, got %d)", depth, d)));
  }
}
