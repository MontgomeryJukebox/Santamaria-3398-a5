# Santamaria-3398-a5

## Adding an Item
The window has a textfield for the serial number, name, and value of an item. Fill these out and hit the "New Item" button on the right to commit these into the 
inventory tracker.

## Removing an Item
Below the controls for adding an item is a textfield with the prompt "Serial Number" besides being consufingly similar and close to the serial number text field for
adding an item, providing this textfield a serial number and hitting "delete item" will detele the item from the inventory tracker.

## Editting an item
Simply double click the desired attribute in the table. Edit, and hit enter. Although the changes may not seem to have taken place, the underlying data has been changed to 
reflect the changes. If it lags, try entering something to the search bar (at the bottom) and clearing it to refresh the table (i swear i re-drew the table and spent way too long
trying to figure out why it didn't want to refresh, but it's something I have to deal with now. I'll keep looking into it though)

## Searching an Item
The bottom text field is a search bar, and accepts regex expressions. All items in the inventory tracker will be filtered by this text
by both serial number and name.

## Sorting by Attribute
Click the appropriate field in the table view. This will sort all the items by that attribute

## Saving to a file
File -> Save as...
provide the file name (without the extension). It will be relative to the working directory.
Then provide the file type (case insensitive). The program will then save the current inventory to the respective file.
## Loading from a file
File -> Load...
Provide the full filename including extension and the program will load the saved data.
