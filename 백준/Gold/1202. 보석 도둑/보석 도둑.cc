#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>

using namespace std;

class Jewelry {
    public:
        int weight;
        int value;

        Jewelry() : weight(0), value(0) {}
        Jewelry(int w, int v) : weight(w), value(v) {}
};

struct CompareWeight {
    bool operator() (const Jewelry& j1, const Jewelry& j2) {
        return j1.weight < j2.weight;
    }
};

struct CompareValue {
    bool operator() (const Jewelry& j1, const Jewelry& j2) {
        return j1.value < j2.value;
    }
};

int main() {

    int n, k;
    cin >> n >> k;

    vector<Jewelry> jewelries(n);
    for (int i = 0; i < n; i++) {
        cin >> jewelries[i].weight >> jewelries[i].value;
    }

    vector<int> bags(k);
    for (int i = 0; i < k; i++) {
        cin >> bags[i];
    }

    sort(jewelries.begin(), jewelries.end(), CompareWeight());
    sort(bags.begin(), bags.end());

    priority_queue<Jewelry, vector<Jewelry>, CompareValue> candidate;
    int idx = 0;
    long answer = 0;
    for (int b : bags) {
        while (idx < n && b >= jewelries[idx].weight) candidate.push(jewelries[idx++]);
        
        if (candidate.empty()) continue;
        answer += candidate.top().value;
        candidate.pop();
    }

    cout << answer << endl;
}