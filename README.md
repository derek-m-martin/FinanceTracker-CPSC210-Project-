# Personal Finance Tracker

## Streamline Your Budgeting and Spending

**What will the application do?**

My idea for this term project is a **personal finance tracker**! The finance tracker will make it easy for not just me, but anyone to have a streamlined and intuitive way in which their spending can be kept track of. Some early feature ideas of mine are:

- Input transactions into the tracker with info, category, date, etc.
- A budgeting portion where users can see their spending contrasted with their budget amount
- Seeing trends in spending habits and more!

**Why I'm Interested in this Idea!**

This project idea came to me pretty quickly as I already maintain a budget spreadsheet of my own to track my work income, spending, and the like. Currently, my spreadsheet allows me to add transactions and categorize them into areas like:

- **Food**
- **Transportation** (e.g., Evo costs and Uber rides)
- **Miscellaneous** (for anything that doesn't have its own category)

As such, an opportunity to migrate this concept from my static Google spreadsheet into an interactive and eventually visual format throughout the term was a *no-brainer* to take. I sure know I need to wrangle my spending habits and know that plenty of other people feel the same way too!

**Who will use it?**

This project will be usable by **anyone** who wants to finally take charge of their spending and understand where their money goes while also knowing why. Finance should be an accessible and widely taught topic as it benefits absolutely everyone on the planet, whether big or small and this serves as a way for me to help out in that regard and possibly make something quite cool along the way.



## User Stories

**1.** As a user, I want to be able to add a new transaction to my finance tracker by specifying the amount, category (e.g., food, transportation, miscellaneous), date, and a brief description.

**2.** As a user, I want to be able to view a list of all my recorded transactions, with options to sort or filter them by date or category.

**3.** As a user, I want to be able to set a budget for each spending category to help manage my expenses.

**4.** As a user, I want to be able to see a summary of my total spending over a selected time period to understand my financial habits.

**5.** As a user, I want to be able to edit or delete a transaction in case I made a mistake or need to update information.

**6.** As a user, I want to be able to save all my transactions and their respective information such as which category they are in, (if I choose to do so.)

**7.** As a user, I want to be prompted to load my saved transactions and accompanying information when the FinanceTracker is started (if I choose to do so.)

**8.** As a user, I want to be able to reset my saved data if I choose to do so.

## Instructions for End User

- **You** can add any number of desired transactions to any of the base categories by navigating to the "Add Transaction" panel with the approrpriate button, and then entering the desired transaction amount in the textbox, choosing the desired category from the drop-down, and then you may choose to add a description if you wish but it is not necessary. Once that is all done you need to click the "Add Transaction" button within the panel and if all is good you will receieve a success message, otherwise it will inform you of any errors!

- **You** can edit or delete any desired transaction that has been added (or loaded from previous data) by navigating to the "Edit Transaction" panel. Once there you will need to enter the transaction identifier of the transaction you would like to modify (can be found in the View Transactions panel). Once you have entered the identifier, go ahead and click the "Search" button which will then populate the remaining data boxes with that corresponding transactions data, if this does not happen it will provide an error message of what went wrong. Once that successfully happens, you can change anything such as the amount, description, or category of the transaction! When you have altered it to your satisfaction then click the "Update" button which will then complete the changes. The alternative option that you can do once you have searched a transaction successfully is to click the "Delete" button which will completely remove the transaction from the data. Keep in mind this is unreversible so only use it if you are sure you want to remove it.

- **You** can also view and then filter all of the transactions that have been added to categories by navigating to the "View Transactions" panel. Once there you are able to view all of your transactions that have been made, and then can choose methods of filtering them from the drop-down menu such as filtering by newest/oldest or by category. Once you choose an option from the drop-down just click the "Apply Filter" button and it will rearrange the transactions to meet your choice!

- **You** can also get a summary report of all your spending (unless you're like me and do NOT want to be reminded of that) you can achieve this as follows. First, navigate to the "Summary" panel, and here you will have two boxes, one to enter a start date and another for an end date. In these boxes you will enter two dates which will encompass the range of transactions to summarize, this could be the start and end of a month for example. The page shows the accepted date format, and once you enter those two dates it's as easy as clicking the "Generate Summary" button which will then show you all of your hopefully good spending habits!

- **You** can locate my visual component while on any of the panels. It is visible above the panel selection buttons and quite hard to miss. I think it fits the finance-theme of my program and makes it just a bit funnier and enjoyable, especially for those who understand it!

- **You** can save the state of my application by either navigating to the "File" button in the top left, clicking it and then choosing "Save Data" which will save your data but not exit you from the program, so more of an intermediary save to ensure you don't lose data while using it. Alternatively, when you click the 'x' button to exit the application, it will also prompt you to save it, and you are free to click 'yes' or 'no' as you so choose.

- **You** can reload the state of my application by either clicking 'yes' or 'no' on the prompt that comes up when you run the application which gives you the option to load data right away. Alternatively like saving you can also navigate to the "File" button in the top left and then clicking "Load Data." **An important note though is if you don't load existing data when you launch the application, add/edit/delete new transactions, but then choose to load your existing data later on, it will overwrite the new transactions you put in.**

## Phase 4 Task 2:

Wed Nov 27 13:29:47 PST 2024
Created a new transaction with the following details: ID: 0 Amount: 50 Category: TRANSPORTATION Description: uber ride
Wed Nov 27 13:29:59 PST 2024
Created a new transaction with the following details: ID: 1 Amount: 200 Category: FOOD Description: costco trip
Wed Nov 27 13:30:14 PST 2024
Changed the transaction with ID: 0's amount to the new amount of: 1200
Wed Nov 27 13:30:14 PST 2024
Changed the transaction with ID: 0's description to: rent
Wed Nov 27 13:30:14 PST 2024
Moved the transaction with ID: 0 from the TRANSPORTATION category to the HOUSING category.
Wed Nov 27 13:30:22 PST 2024
Deleted the transaction with ID: 1
Wed Nov 27 13:30:36 PST 2024
Created a new transaction with the following details: ID: 2 Amount: 5000 Category: ENTERTAINMENT Description: exceed entertainment budget
Wed Nov 27 13:30:36 PST 2024
The ENTERTAINMENT category has exceeded its assigned budget of: $1000