
function tinhToan(phepToan) {
    let val1 = document.getElementById('num1').value.trim();
    let val2 = document.getElementById('num2').value.trim();
    let ketQuaText = document.getElementById('ketQuaText');

    // Kiểm tra dữ liệu đầu vào
    if (val1 === "" || val2 === "") {
        ketQuaText.innerText = "Vui lòng nhập đầy đủ hai số!";
        return;
    }

    let so1 = Number(val1);
    let so2 = Number(val2);

    // Kiểm tra dữ liệu có hợp lệ không
    if (!Number.isFinite(so1) || !Number.isFinite(so2)) {
        ketQuaText.innerText = "Vui lòng nhập số hợp lệ!";
        return;
    }

    // Kiểm tra giá trị thực tế khi bấm nút
    console.log("Phép toán:", phepToan);
    console.log("Số thứ nhất:", so1);
    console.log("Số thứ hai:", so2);

    let ketQua;

    switch (phepToan) {
        case '+':
            ketQua = so1 + so2;
            break;

        case '-':
            ketQua = so1 - so2;
            break;

        case '*':
            ketQua = so1 * so2;
            break;

        case '/':
            if (so2 === 0) {
                ketQuaText.innerText = "Không thể chia cho số 0!";
                return;
            }

            ketQua = so1 / so2;
            break;

        default:
            ketQuaText.innerText = "Phép toán không hợp lệ!";
            return;
    }

    if (!Number.isFinite(ketQua)) {
        ketQuaText.innerText = "Kết quả quá lớn hoặc không hợp lệ!";
        return;
    }

    ketQuaText.innerText = "Kết quả: " + ketQua;
}
