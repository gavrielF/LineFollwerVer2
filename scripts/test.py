import numpy as np
import matplotlib.pyplot as plt

# data = np.random.randint(3, 7, (10, 1, 1, 80))

data = [1,3,4]

# with open('C:\Users\Gavriel\Documents\lego\logs\TestCali42.txt') as f:

def read_integers(filename):
    array = []
    with open(filename) as f:
        for x in f:
           for i in x.split(" "):
               array.append(i)
    return array

output = read_integers(r'C:\Users\Gavriel\Documents\lego\logs\TestCali42.txt')



newdata = np.squeeze(output) # Shape is now: (10, 80)
plt.plot(newdata) # plotting by columns
plt.show()