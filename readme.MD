## KotlinBottomActionSheet

### Creating an actionsheet module in kotlin

### Usage

#### Creating the Action Sheet in Activity

```
    ActionSheet.create(this)
```

#### Adding Menu with text and menu click listener to the action sheet created in Activity

```
    class ClickListener:ActionItemClickListener {
        override fun onClick() {

        }
    }
    ActionSheet.addMenu(text,ClickListener())
```

#### Showing the action sheet
```
    ActionSheet.addToParent(this)
```


#### Note: You must call create at first, followed by addMenu and then addToParent. 'this' refers to activity object


### Demo
<img src="https://github.com/Anwesh43/KotlinBottomActionSheet/blob/master/demo/bottomactionsheetkt.gif" alt="demo" width="300px" height="500px">
