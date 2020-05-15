from tkinter import *

root = Tk()
root.config({"background": "grey"})
root.title("Calculator")
entry = Entry(root, width=21, borderwidth=5)
entry.config({"justify": "right", "font": "Tahoma", "relief": "ridge"})

global f_num
global maths


def button_clear(event=None):
    entry.delete(0, END)

def button_clear_everything(event=None):
    entry.delete(0, END)
    global f_num
    f_num is None

def button_add(event=None):
    first_number = entry.get()
    global maths
    maths = '+'
    global f_num
    f_num = float(first_number)
    entry.delete(0, END)

def button_sub(event=None):
    first_number = entry.get()
    global maths
    maths = '-'
    global f_num
    f_num = float(first_number)
    entry.delete(0, END)

def button_mul(event=None):
    first_number = entry.get()
    global maths
    maths = '*'
    global f_num
    f_num = float(first_number)
    entry.delete(0, END)

def button_div(event=None):
    first_number = entry.get()
    global maths
    maths = '/'
    global f_num
    f_num = float(first_number)
    entry.delete(0, END)

def button_opp():
    if len(entry.get()) >= 1:
        current = float(entry.get()) * -1
        current_string = str(current)
        entry.insert(0, current_string)

def button_point(event=None):
    is_point = False
    for char in entry.get():
        if char == '.':
            is_point = True
    if not is_point:
        button_click('.')

def get_result(num1, num2):
    if maths == '+':
        return num1 + num2
    if maths == '-':
        return num1 - num2
    if maths == '*':
        return num1 * num2
    if num2 == 0 and maths == '/':
        return 'Impossible division by zero'
    if maths == '/':
        return num1 / num2

def button_equal(event=None):
    s_num = float(entry.get())
    result = get_result(f_num, s_num)

    if result != 'No division by zero': result_string = str(round(result, 6))
    if result_string.endswith(".0"): result_string = result_string[0: len(result_string)-2]
    else: result_string = str(result)

    entry.delete(0, END)
    entry.insert(0, result_string)

def button_del(event=None):
    if len(entry.get()) >= 1: entry.delete(len(entry.get())-1, END)

def button_click(number, event=None):
    current = entry.get()
    entry.delete(0, END)
    entry.insert(0, str(current) + str(number))


def insert_0(event=None):
    current = entry.get()
    entry.delete(0, END)
    entry.insert(0, str(current) + str(0))


def insert_1(event=None):
    current = entry.get()
    entry.delete(0, END)
    entry.insert(0, str(current) + str(1))


def insert_2(event=None):
    current = entry.get()
    entry.delete(0, END)
    entry.insert(0, str(current) + str(2))


def insert_3(event=None):
    current = entry.get()
    entry.delete(0, END)
    entry.insert(0, str(current) + str(3))


def insert_4(event=None):
    current = entry.get()
    entry.delete(0, END)
    entry.insert(0, str(current) + str(4))


def insert_5(event=None):
    current = entry.get()
    entry.delete(0, END)
    entry.insert(0, str(current) + str(5))


def insert_6(event=None):
    current = entry.get()
    entry.delete(0, END)
    entry.insert(0, str(current) + str(6))


def insert_7(event=None):
    current = entry.get()
    entry.delete(0, END)
    entry.insert(0, str(current) + str(7))


def insert_8(event=None):
    current = entry.get()
    entry.delete(0, END)
    entry.insert(0, str(current) + str(8))


def insert_9(event=None):
    current = entry.get()
    entry.delete(0, END)
    entry.insert(0, str(current) + str(9))


button_c = Button(root, text="C", padx=19, pady=20, borderwidth=2, command=button_clear)
button_ce = Button(root, text="CE", padx=17, pady=20, borderwidth=2, command=button_clear_everything)
button_delete = Button(root, text="del", padx=15.5, pady=20, borderwidth=2, command=button_del)
button_1 = Button(root, text="1", padx=20, pady=20, borderwidth=2, command=lambda: button_click(1))
button_2 = Button(root, text="2", padx=20, pady=20, borderwidth=2, command=lambda: button_click(2))
button_3 = Button(root, text="3", padx=20, pady=20, borderwidth=2, command=lambda: button_click(3))
button_4 = Button(root, text="4", padx=20, pady=20, borderwidth=2, command=lambda: button_click(4))
button_5 = Button(root, text="5", padx=20, pady=20, borderwidth=2, command=lambda: button_click(5))
button_6 = Button(root, text="6", padx=20, pady=20, borderwidth=2, command=lambda: button_click(6))
button_7 = Button(root, text="7", padx=20, pady=20, borderwidth=2, command=lambda: button_click(7))
button_8 = Button(root, text="8", padx=20, pady=20, borderwidth=2, command=lambda: button_click(8))
button_9 = Button(root, text="9", padx=20, pady=20, borderwidth=2, command=lambda: button_click(9))
button_0 = Button(root, text="0", padx=20, pady=20, borderwidth=2, command=lambda: button_click(0))
button_multiply = Button(root, text="X", padx=20, pady=20, borderwidth=2, command=button_mul)
button_divide = Button(root, text="÷", padx=19, pady=20, borderwidth=2, command=button_div)
button_subtract = Button(root, text="-", padx=21, pady=20, borderwidth=2, command=button_sub)
button_addition = Button(root, text="+", padx=19, pady=20, borderwidth=2, command=button_add)
button_opposite = Button(root, text="+/-", padx=14, pady=20, borderwidth=2, command=button_opp)
button_p = Button(root, text=".", padx=22, pady=20, borderwidth=2, command=button_point)
button_equals_to = Button(root, text="=", padx=19, pady=20, borderwidth=2, command=button_equal)


# buttons on screen
def display_buttons():
    entry.grid(row=0, column=0, columnspan=4, padx=10, pady=10, ipady=10)

    button_c.grid(row=1, column=0, columnspan=1, pady=2)
    button_ce.grid(row=1, column=1, columnspan=1, pady=2)
    button_delete.grid(row=1, column=2, columnspan=1, pady=2)
    button_multiply.grid(row=1, column=3, columnspan=1, pady=2)

    button_7.grid(row=2, column=0, columnspan=1, pady=2)
    button_8.grid(row=2, column=1, columnspan=1, pady=2)
    button_9.grid(row=2, column=2, columnspan=1, pady=2)
    button_divide.grid(row=2, column=3, columnspan=1, pady=2)

    button_4.grid(row=3, column=0, columnspan=1, pady=2)
    button_5.grid(row=3, column=1, columnspan=1, pady=2)
    button_6.grid(row=3, column=2, columnspan=1, pady=2)
    button_subtract.grid(row=3, column=3, columnspan=1, pady=2)

    button_1.grid(row=4, column=0, columnspan=1, pady=2)
    button_2.grid(row=4, column=1, columnspan=1, pady=2)
    button_3.grid(row=4, column=2, columnspan=1, pady=2)
    button_addition.grid(row=4, column=3, columnspan=1, pady=2)

    button_opposite.grid(row=5, column=0, columnspan=1, pady=2)
    button_0.grid(row=5, column=1, columnspan=1, pady=2)
    button_p.grid(row=5, column=2, columnspan=1, pady=2)
    button_equals_to.grid(row=5, column=3, columnspan=1, pady=2)


def add_listeners():
    root.bind('+', button_add)
    root.bind('-', button_sub)
    root.bind('*', button_mul)
    root.bind('/', button_div)
    root.bind('.', button_point)
    root.bind('<Return>', button_equal)
    root.bind('<BackSpace>', button_del)
    root.bind('<Delete>', button_clear)

    root.bind('<KP_0>', insert_0)
    root.bind('<KP_1>', insert_1)
    root.bind('<KP_2>', insert_2)
    root.bind('<KP_3>', insert_3)
    root.bind('<KP_4>', insert_4)
    root.bind('<KP_5>', insert_5)
    root.bind('<KP_6>', insert_6)
    root.bind('<KP_7>', insert_7)
    root.bind('<KP_8>', insert_8)
    root.bind('<KP_9>', insert_9)
    root.bind('<KP_Decimal>', button_point)

    root.bind('0', insert_0)
    root.bind('1', insert_1)
    root.bind('2', insert_2)
    root.bind('3', insert_3)
    root.bind('4', insert_4)
    root.bind('5', insert_5)
    root.bind('6', insert_6)
    root.bind('7', insert_7)
    root.bind('8', insert_8)
    root.bind('9', insert_9)


add_listeners()
display_buttons()
root.mainloop()
