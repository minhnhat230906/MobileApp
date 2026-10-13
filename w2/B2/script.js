// 1. Tạo mảng danh sách 5 sinh viên (có người bị thiếu email hoặc phone là null)
const students = [
    { name: 'Nguyễn Hoàng Nam', email: 'nam@sv.edu.vn', phone: '0905 123 456' },
    { name: 'Trần Ngọc Mai', email: null, phone: '0918 456 789' },
    { name: 'Lê Quốc Huy', email: 'huy@sv.edu.vn', phone: null },
    { name: 'Phạm Thùy Linh', email: null, phone: null },
    { name: 'Võ Minh Khang', email: 'khang@sv.edu.vn', phone: '0937 222 333' }
];

// Lấy các element từ HTML
const container = document.getElementById('student-list');
const totalText = document.getElementById('total-students');
const missingEmailText = document.getElementById('missing-email');

let htmlContent = '';
let missingEmailCount = 0;

// 3 & 5. Dùng vòng lặp for để duyệt danh sách và đếm số sinh viên thiếu email
for (let i = 0; i < students.length; i++) {
    let sv = students[i];

    // Đếm số lượng thiếu email
    if (sv.email === null) {
        missingEmailCount++;
    }

    // 4. Dùng toán tử nullish coalescing (??) để nếu null thì hiện "Chưa cập nhật"
    let emailDisplay = sv.email ?? "Chưa cập nhật";
    let phoneDisplay = sv.phone ?? "Chưa cập nhật";

    let emailClass = sv.email === null ? 'missing' : '';
    let phoneClass = sv.phone === null ? 'missing' : '';

    // Tạo HTML cho từng thẻ sinh viên
    htmlContent += `
        <div class="student-card">
            <h4>${sv.name}</h4>
            <p class="${emailClass}">Email: ${emailDisplay}</p>
            <p class="${phoneClass}">SĐT: ${phoneDisplay}</p>
        </div>
    `;
}

// Đổ dữ liệu HTML vào giao diện
container.innerHTML = htmlContent;

// Hiển thị thông tin tổng kết ở cuối
totalText.innerText = `Tổng: ${students.length} sinh viên`;
missingEmailText.innerText = `Thiếu email: ${missingEmailCount}`;