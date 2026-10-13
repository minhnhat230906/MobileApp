// 2. Viết hàm nhận vào điểm và trả về xếp loại dùng if / else
function xepLoaiHocLuc(diem) {
    if (diem < 0 || diem > 10) {
        return "Không hợp lệ";
    } else if (diem >= 8.0) {
        return "Giỏi";
    } else if (diem >= 6.5) {
        return "Khá";
    } else if (diem >= 5.0) {
        return "Trung bình";
    } else {
        return "Yếu";
    }
}

// Hàm gán màu sắc (background và text) cho từng loại xếp hạng
function getBadgeStyle(ketQua) {
    switch (ketQua) {
        case "Giỏi":
            return { bg: "#e8f5e9", color: "#2e7d32" }; // Xanh lá
        case "Khá":
            return { bg: "#e3f2fd", color: "#1565c0" }; // Xanh dương
        case "Trung bình":
            return { bg: "#fff3e0", color: "#ef6c00" }; // Cam
        case "Yếu":
            return { bg: "#ffebee", color: "#c62828" }; // Đỏ
        default:
            return { bg: "#f3f4f6", color: "#4b5563" }; // Xám (Không hợp lệ)
    }
}

// 1. Khai báo biến điểm chính có giá trị 8.5
const diemChinh = 8.5;
const ketQuaChinh = xepLoaiHocLuc(diemChinh);

// Hiển thị điểm chính lên giao diện
document.getElementById('main-score').innerText = diemChinh;
const mainBadge = document.getElementById('main-badge');
mainBadge.innerText = ketQuaChinh;
const mainStyle = getBadgeStyle(ketQuaChinh);
mainBadge.style.backgroundColor = mainStyle.bg;
mainBadge.style.color = mainStyle.color;

// 4. Phần "Kiểm tra thêm": gọi hàm với các điểm 9, 7, 5.5, 3 và 11
const danhSachKiemTra = [10, 7.9, 6, 4.5, 21];
const container = document.getElementById('test-list-container');
let htmlContent = '';

for (let i = 0; i < danhSachKiemTra.length; i++) {
    let diem = danhSachKiemTra[i];
    let ketQua = xepLoaiHocLuc(diem);
    let style = getBadgeStyle(ketQua);

    htmlContent += `
        <div class="test-row">
            <span>Điểm ${diem}</span>
            <span class="test-badge" style="background-color: ${style.bg}; color: ${style.color};">
                ${ketQua}
            </span>
        </div>
    `;
}

container.innerHTML = htmlContent;