# Zip User Guide

## Introduction
**Zip** is a lightweight Command Line Interface (CLI) 
task manager designed to help users manage 
their daily tasks efficiently.

Zip allows users to:
- Add different types of tasks 
- Track deadlines 
- Manage events 
- Mark tasks as done 
- View upcoming deadlines 
- Search and delete tasks easily

## Quick Start
1. Ensure you have Java 17 installed.
2. Download the `zip.jar` file.
3. Open a terminal in the folder containing the `.jar` file.
4. Run the application:

```
java -jar zip.jar
```
## Features
### 1. Adding a todo task
#### Adds a simple task without any date.
### Format

```
todo {task name}
```
***Note:*** There must be exactly **one** space between 
`todo` and the task name.

### Example
```
todo laundry
```

### Output
```
Got it. I have added this task:
[T][] laundry
Now you have 1 tasks in the list.
```

### 2. Adding a Deadline Task
#### Adds a task that must be completed by a specific date.
### Format

```
deadline {task name} /by {YYYY-MM-DD}
```
- The date must be in `YYYY-MM-DD` format.
- Any deviation from the format will result 
in an **Invalid Input** Error.

### Example
```
deadline CS2103T iP /by 2026-02-23
```

### Output
```
Got it. I have added this task:
[D][] CS2103T iP (by:Feb 23 2026)
Now you have 2 tasks in the list.
```

### 3. Adding an Event Task
#### Adds a task that spans a time period.
### Format

```
event {task name} /from {YYYY-MM-DD} /to {YYYY-MM-DD}
```
- Both dates must be in `YYYY-MM-DD` format.

### Example
```
event CNY celebration /from 2026-02-17 /to 2026-02-18
```

### Output
```
Got it. I have added this task:
[E][] CNY celebration (from: Feb 17 2026 to: Feb 18 2026)
Now you have 3 tasks in the list.
```

### 4. Listing All Tasks
#### Displays all tasks in the order they were added.
### Format

```
list
```
- The command must be exactly `list` without any trailing spaces.

### Output
```
Here are the tasks in your list:
1.[T][ ] laundry
2.[D][ ] CS2103T iP (by: Feb 23 2026)
3.[E][ ] CNY celebration (from: Feb 17 2026 to: Feb 18 2026)
```
### Explanation of Format
```
[T][X] task description
```
- First bracket : Task type (`T`, `D`, `E`)
- Second bracket : Completion status
  - `[ ]` = Not done
  - `[X]` = Done
- Task description : Name + date (if applicable)

### 5. Marking a task as done
#### Marks a task as completed.
### Format

```
mark {index}
```
- `{index}` refers to the task number shown in the `list`

### Example
```
mark 1
```

### Output
```
Nice. I've marked this task as done:
[T][X] todo laundry
```
### 6. Unmarking a task
#### Marks a completed task as not done.
### Format

```
unmark {index}
```
- `{index}` refers to the task number shown in the `list`

### Example
```
unmark 1
```

### Output
```
OK. I've marked this task as not done yet:
[T][] todo laundry
```

### 7. Deleting a task
#### Deletes a task from the list.
### Format

```
delete {index}
```
- `{index}` refers to the task number shown in the `list`

### Example
```
delete 1
```

### Output
```
Noted. I have removed this task:
[T][] todo laundry
```
***Note:*** After deleting a task, please check the list again 
for the updated task numbers.

### 8. Finding tasks
#### Searches for tasks containing a specific keyword.
### Format

```
find {keyword}
```

### Example
```
find CS2103T
```

### Output
```
Here are the tasks in your list:
1.[D][] CS2103T iP (by: Feb 23 2026)
```

### 9. Viewing upcoming deadlines
#### Displays all undone deadline tasks sorted in chronological order.
### Format

```
due
```
- The command must be exactly `due` without any trailing spaces.

### Output
```
Here are the upcoming deadlines sorted in chronological order:
1.[D][ ] CS2103T iP (by: Feb 23 2026)
2.[D][ ] CS3210 Midterm (by: March 5 2026)
```

### 10. Exiting the application
#### Closes Zip safely.
### Format

```
bye
```

## Thank you for Using Zip

Zip is designed to be simple, fast, and efficient for daily task management.
By keeping commands structured and consistent, Zip ensures a smooth and predictable user experience.

We hope Zip helps you stay organised and productive every day.




