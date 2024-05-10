#!/usr/bin/env python3
from utils import mnist_reader
import numpy as np
from sklearn.linear_model import LogisticRegression
from sklearn.metrics import accuracy_score
import matplotlib.pyplot as plt
from sklearn import svm

def preprocessing():
    #load data
    X_train, y_train = mnist_reader.load_mnist('data/fashion', kind='train')
    X_test, y_test = mnist_reader.load_mnist('data/fashion', kind='t10k')

    #rescale image values from [0,255] to [0,1] and discard images not in class 0 or 6
    X_train = np.array([[pixel/255.0 for pixel in X_train[image]] for image in range(0, X_train.shape[0]) if (y_train[image] == 0 or y_train[image] == 6)])
    X_test = np.array([[pixel/255.0 for pixel in X_test[image]] for image in range(0, X_test.shape[0]) if (y_test[image] == 0 or y_test[image] == 6)])
    #discard image labels if they aren't in class 0 or 6
    y_train = [label for label in y_train if (label == 0 or label == 6)]
    y_test = [label for label in y_test if (label == 0 or label == 6)]
    X_train = X_train[:2000]
    y_train = y_train[:2000]
    return [X_train, y_train, X_test, y_test]

def logistic_regression(X_train, y_train, X_test, y_test):
    #Fit Model
    test_accuracy = []
    train_accuracy = []
    c1s = []
    for i in range(0,30):
        c1 = pow(.5, i)
        c1s+=[c1]
        model = LogisticRegression(C=c1, multi_class="multinomial", solver="sag", penalty="l2", max_iter = 100)
        model.fit(X_train, y_train)
        #Use model on test data
        y_pred_test = model.predict(X_test)
        y_pred_train = model.predict(X_train)
        
        accuracy_test = accuracy_score(y_test, y_pred_test)
        accuracy_train = accuracy_score(y_train, y_pred_train)
        test_accuracy += [accuracy_test]
        train_accuracy += [accuracy_train]

    #show results
    plt.plot(c1s, test_accuracy, color = "green", label = "Test Accuracy")
    plt.plot(c1s, train_accuracy, color = "blue", label = "Training Accuracy")
    plt.title("Effect of Regularization on Accuracy for Logistic Regression")
    plt.xlabel("C")
    plt.ylabel("Accuracy(%)")
    plt.legend()
    plt.show()
    return [test_accuracy, c1s]

def SVM(X_train, y_train, X_test, y_test, kernel_in):
    test_accuracy = []
    train_accuracy = []
    c1s = []
    for i in range(0,30):
        c1 = pow(.5, i)
        c1s+=[c1]
        model = svm.SVC(kernel = kernel_in, C = c1)
        model.fit(X_train, y_train)
        #Use model on test data
        y_pred_test = model.predict(X_test)
        y_pred_train = model.predict(X_train)
        
        accuracy_test = accuracy_score(y_test, y_pred_test)
        accuracy_train = accuracy_score(y_train, y_pred_train)
        test_accuracy += [accuracy_test]
        train_accuracy += [accuracy_train]

    #show results
    plt.plot(c1s, test_accuracy, color = "green", label = "Test Accuracy")
    plt.plot(c1s, train_accuracy, color = "blue", label = "Training Accuracy")
    plt.title("Effect of Regularization on Accuracy for Support Vector Machine")
    plt.xlabel("C")
    plt.ylabel("Accuracy(%)")
    plt.legend()
    plt.show()
    return [test_accuracy, c1s]

def k_fold_cross_val(X_train, y_train, X_test, y_test):
    set_size = int(X_train.shape[0]/5)
    lr_accuracy = []
    lr_mean_accuracy = []
    svm_accuracy = []
    svm_mean_accuracy = []
    for i in range(0,int(X_train.shape[0]/set_size)):
        x_validation_set = X_train[i*set_size:(i+1)*set_size]
        y_validation_set = y_train[i*set_size:(i+1)*set_size]
        if(i == 0):
            x_training_set = X_train[set_size:5*set_size]
            y_training_set = y_train[set_size:5*set_size]
        elif(i == 4):
            x_training_set = X_train[:4*set_size]
            y_training_set = y_train[:4*set_size]
        else:
            x_training_set = np.concatenate((X_train[0:(i)*set_size], X_train[(i+1)*set_size:5*set_size]))
            y_training_set = y_train[0:(i)*set_size] + y_train[(i+1)*set_size:5*set_size]
        lr_accuracy += [logistic_regression(x_training_set, y_training_set, x_validation_set, y_validation_set)[0]]
        svm_accuracy += [SVM(x_training_set, y_training_set, x_validation_set, y_validation_set, "linear")[0]]
    for c in range(0,len(lr_accuracy[0])):
        lr_mean_accuracy += [(lr_accuracy[0][c] + lr_accuracy[1][c] + lr_accuracy[2][c] + lr_accuracy[3][c] + lr_accuracy[4][c])/5]
        svm_mean_accuracy += [(svm_accuracy[0][c] + svm_accuracy[1][c] + svm_accuracy[2][c] + svm_accuracy[3][c] + svm_accuracy[4][c])/5]
    lr_c_max = lr_mean_accuracy.index(max(lr_mean_accuracy))
    svm_c_max = svm_mean_accuracy.index(max(svm_mean_accuracy))
    lr_result = logistic_regression(X_train, y_train, X_test, y_test)[0][lr_c_max]
    svm_result = logistic_regression(X_train, y_train, X_test, y_test)[0][svm_c_max]
    print("LR 95% CI: [",str(lr_result - .0136),",", str(lr_result + .0136),"]")
    print("SVM 95% CI: [",str(svm_result - .0136),",", str(svm_result + .0136),"]")

                                          


def main():
    data = preprocessing()
    logistic_regression(data[0], data[1], data[2], data[3])
    SVM(data[0], data[1], data[2], data[3], "linear")
    #SVM(data[0], data[1], data[2], data[3], "rbf")
    k_fold_cross_val(data[0], data[1], data[2], data[3])

if __name__ == "__main__":
    main()