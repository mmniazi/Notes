#include <iostream>

using namespace std;

int main() {
    cout << "Hello, World!" << endl;

    const double PI = 3.14;
    char myGrade = 'A';
    bool isHappy = true;
    int myAge = 23;
    float anotherPi = 3.14;

    cout << PI << endl;

    cout << "Size of int: " << sizeof(myAge)
         << endl;

    cout << "Largest Int32: " << INT32_MAX << " and size is " << sizeof(INT32_MAX) << endl;

    // Multiplication and Division is carried out first then addition and subtraction

    cout << 4 / 5 << endl;
    cout << ((float) 4) / 5 << endl;

    // Arrays
    int myArr[10]; // Initialized to 0
    int myAnArr[4] = {1, 2, 3, 4};
    int myAnAnArr[] = {1, 2, 3, 4};

    cout << "myArr[1]: " << myArr[1] << endl;

    char chars[2][2] = {{'A', 'B'},
                        {'C', 'D'}};

    cout << "chars[1][1]: " << chars[1][1] << endl;

    cout << "rand: " << rand() % 100 << endl;

    string me = "Mohsin";

    cout << me << endl;


//    string number = "";
//    do {
//        cout << "write 4 to continue:" << endl;
//        getline(cin, number);
//    } while (stoi(number) != 4); // stoi and stod are used to convert string to int and double

    char charArr[] = {';'};
    string combined = "Combining string with chararray";
    cout << combined + charArr << endl;
    cout << combined.size() << combined.empty() << endl;

    // Copy value
    string substringexmpl = combined.assign(combined, 0, 3);
    cout << substringexmpl << endl;

    return 0;
}
