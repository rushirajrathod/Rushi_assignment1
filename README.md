## Screenshots  

<p align="center">
  <img src="https://github.com/user-attachments/assets/a442d68e-28ed-47bd-aa6c-3910077acf52" alt="Main Screen" width="250"/>
  <img src="https://github.com/user-attachments/assets/d727d19e-74b0-429a-8583-1a0e02859f56" alt="Budget Section" width="250"/>
  <img src="https://github.com/user-attachments/assets/ead52252-746c-4162-94c1-d72fbc41ef8a" alt="Expenses Screen" width="250"/>
</p>

---

# SOFE 4640U: Mobile Application Development

## 1. Introduction
In this assignment 1, I developed a mobile application called **Budgetly**. The app is designed to calculate monthly mortgage payments (EMI), track income and expenses, and clearly show whether the user ends the month with savings or a deficit. It also includes a second screen for adding detailed expenses, which gives users a more realistic picture of their financial situation. The main goal of the project was to put into practice the fundamentals of Android development using Kotlin. 

## 2. Layouts
The app’s structure is built using ConstraintLayout along with Material Design cards to keep the interface simple and organized. The main screen is divided into two sections:  

- **Mortgage Section** – This part groups together the loan amount, interest rate, and tenure inputs, along with a button to calculate the monthly EMI.  
- **Budget Section** – This section collects the user’s income and expenses and shows the final monthly balance.  

By wrapping each section inside a card, the layout looks clean and guides the user step by step: first calculate EMI, then check savings or deficit. On the second screen, which handles detailed expenses, a vertical layout is used with a list view (RecyclerView) so users can add as many expense items as needed.

## 2. Views
The app makes use of a variety of views to handle input and output:  

- **TextInputEditText** for loan amount, rate, tenure, income, and expenses, ensuring inputs are typed correctly.  
- **MaterialButton** for actions such as Calculate EMI, Compute Budget, and Add Expenses.  
- **TextView** for displaying results like the calculated EMI, total expenses, and final savings or deficit.  
- **RecyclerView** on the detailed expenses screen, which dynamically lists all added expenses.  

These views work together so that the app feels interactive and responsive, with inputs immediately affecting the displayed results.

## 3. Intents
Two types of intents are demonstrated in the project:  

- **Explicit Intent**: Used when navigating from the main screen to the detailed expenses screen (ExpensesActivity). After the user adds expenses and taps Done, the total is sent back to the main screen using setResult(). This makes it possible for one part of the app to communicate directly with another.  
- **Implicit Intent**: Initially, the app had a share feature, but since it was removed, the focus is now on the explicit navigation. The explicit intent is enough to show how activities can pass data back and forth, which was one of the key goals of the assignment.  

## 4. Conclusion
The **Budgetly** application helps to plan your finances. It combines structured layouts, meaningful views, and inter-activity navigation to solve a simple problem: helping users see their monthly savings or deficit after considering mortgage payments and expenses.  

This project gave me hands-on experience with Android Studio and Kotlin, and helped me understand how layouts, views, and intents all fit together in a real application.  




