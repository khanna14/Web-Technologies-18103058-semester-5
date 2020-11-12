function validate_parenthesis() {
    let string = prompt("Enter string:") // Input

    let stack = [] // Stack
    let dictionary = {
        "(": ")",
        "[": "]",
        "{": "}"
    } // Dictionary for opposite pairs

    for (let i = 0; i < string.length; i++) {
        if (string[i] === "(" || string[i] === "[" || string[i] === "{") {
            // Opening bracket
            stack.push(string[i])
        } else if (string[i] === ")" || string[i] === "]" || string[i] === "}") {
            // Closing bracket

            if (stack.length === 0) {
                // Stack empty
                console.log("Invalid")
                alert("Invalid")
                return
            }
            let last = stack[stack.length - 1]
            if (string[i].localeCompare(dictionary[last]) === 0) {
                // Pair matched
                stack.pop()
            } else {
                console.log("Invalid")
                alert("Invalid")
                return
            }
        } else {
            // Invalid characters
            console.log("Invalid Input!")
            alert("Invalid Input!")
            return
        }

    }
    if (stack.length === 0) {
        // Valid Parenthesis String as Stack is empty
        console.log("Valid")
        alert("Valid")
    } else {
        console.log("Invalid")
        alert("Invalid")
    }
}
