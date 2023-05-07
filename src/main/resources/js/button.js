AJS.$(document).on('click', '#button-spin-1, #button-spin-2', function() {
    var that = this;
    if (!that.isBusy()) {
        that.busy();

        setTimeout(function() {
            that.idle();
        }, 2000);
    }
});