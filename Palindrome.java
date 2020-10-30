public static boolean isPalindrome(int x) {
		if (x < 0) return false;
		List<Integer> xList = new ArrayList<>();
		while (x > 0) {
			xList.add(x % 10);
			x = x / 10;
		}
		int left = 0, right = xList.size() - 1;
		while (left < right) {
			if (xList.get(left) != xList.get(right)) {
				return false;
			}
			left++;
			right--;
		}
		return true;
	}
