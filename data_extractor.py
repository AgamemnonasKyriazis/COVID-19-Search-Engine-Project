import csv
import sys
import os
from tkinter import filedialog

save_path = input("save_path= ")
line_limit = int(input("line_limit= "))
with open('covid19_articles_20201231.csv', encoding="utf8") as csv_file:
    csv_reader = csv.reader(csv_file, delimiter=',')
    line_count = 0
    for row in csv_reader:
        if line_count == 0:
            print(f'Column names are {", ".join(row)}')
            line_count += 1
        else:
            text_file = open(''.join([save_path, str(line_count), '.txt']), 'w', encoding="utf8")
            if row[0] == "":
                print("Anonymous Author", line_count)
                row[0] = "Anonymous Author"
            text_file.write(row[3]+'\n'+row[0]+'\n'+row[5])
            text_file.flush()
            text_file.close()
            line_count += 1
            #
        if line_count == line_limit:
            print('Done!')
            break
    print(f'Processed {line_count} lines.')
