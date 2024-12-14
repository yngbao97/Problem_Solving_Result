#include <iostream>
#include <algorithm>
using namespace std;

int main() {

    int n;
    cin >> n;

    int nums[n];
    for (int i = 0; i < n; i++) {
        cin >> nums[i];
    }

    sort(nums, nums + n);
    int answer = 0;

    for (int i = 0; i < n; i++) {
        int left = 0;
        int right = n-1;

        while (left < right) {
            if (left == i) left++;
            if (right == i) right--;
            if (left >= right) break;

            if (nums[left] + nums[right] > nums[i]) right--;
            else if (nums[left] + nums[right] < nums[i]) left++;
            else {
                answer++;
                break;
            }
        }
    }

    cout << answer << "\n";
}