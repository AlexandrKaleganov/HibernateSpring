function exit() {
    $.ajax({
        type: "POST",
        url: "./",
        data: {exit: "exit"}
    })
};