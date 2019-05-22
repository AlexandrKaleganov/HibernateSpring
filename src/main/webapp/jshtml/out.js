function exit() {
    console.log("exit")
    $.ajax({
        type: "POST",
        url: "./",
        data: {exit: "exit"}
    })
};