$(document).ready(function () {
    // Activate tooltip
    $('[data-toggle="tooltip"]').tooltip();

    // Select/Deselect checkboxes
    var checkbox = $('table tbody input[type="checkbox"]');
    $("#selectAll").click(function () {
        if (this.checked) {
            checkbox.each(function () {
                this.checked = true;
            });
        } else {
            checkbox.each(function () {
                this.checked = false;
            });
        }
    });
    checkbox.click(function () {
        if (!this.checked) {
            $("#selectAll").prop("checked", false);
        }
    });
});

$(document).ready(function(){
    // Bắt sự kiện khi click vào nút "Edit"
    $('.edit').on('click', function(){
        // Lấy ID của sản phẩm được chọn
        var productId = $(this).data('id');
        // Đặt giá trị của action của form chỉnh sửa để truyền ID của sản phẩm vào
        $('#editProductModal form').attr('action', '/editProduct/' + productId);
        // Hiển thị modal form chỉnh sửa
        $('#editProductModal').modal('show');
    });
});

