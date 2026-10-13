// 1. Khai báo danh sách sinh viên gồm name và score (có thể là null)
const students = [
    { name: 'Nguyễn Văn An', score: 9.0 },
    { name: 'Trần Thị Bình', score: 7.2 },
    { name: 'Lê Minh Chí', score: null },
    { name: 'Phạm Quốc Dũng', score: 4.5 },
    { name: 'Võ Thanh Em', score: 5.8 }
];

// Hàm xếp loại học lực (dùng lại hàm từ Bài 2)
function xepLoaiHocLuc(diem) {
    if (diem === null) return "Chưa có điểm";
    if (diem < 0 || diem > 10) return "Không hợp lệ";
    if (diem >= 8.0) return "Giỏi";
    if (diem >= 6.5) return "Khá";
    if (diem >= 5.0) return "Trung bình";
    return "Yếu";
}

// Lấy màu sắc cho chữ xếp loại
function getColorByRank(rank) {
    switch (rank) {
        case "Giỏi": return "#2e7d32";      // Xanh lá
        case "Khá": return "#1565c0";       // Xanh dương
        case "Trung bình": return "#ef6c00";// Cam
        case "Yếu": return "#c62828";       // Đỏ
        default: return "#9ca3af";          // Xám
    }
}

// 3. Dùng vòng lặp for + if để tính toán các số liệu tổng hợp
let tongDiem = 0;
let soLuongCoDiem = 0;
let soSinhVienGioi = 0;
let soSinhVienChuaCoDiem = 0;
let diemCaoNhat = -1;

for (let i = 0; i < students.length; i++) {
    let sv = students[i];

    if (sv.score === null) {
        soSinhVienChuaCoDiem++;
    } else {
        tongDiem += sv.score;
        soLuongCoDiem++;

        // Tìm điểm cao nhất
        if (sv.score > diemCaoNhat) {
            diemCaoNhat = sv.score;
        }

        // Đếm số sinh viên Giỏi (score >= 8.0)
        if (sv.score >= 8.0) {
            soSinhVienGioi++;
        }
    }
}

// Tính điểm trung bình lớp (bỏ qua người chưa có điểm)
let diemTrungBinhLop = soLuongCoDiem > 0 ? (tongDiem / soLuongCoDiem).toFixed(2) : "0.00";
if (diemCaoNhat === -1) diemCaoNhat = 0;

// Đưa kết quả tổng hợp lên thẻ header phía trên
document.getElementById('avg-score').innerText = diemTrungBinhLop;
document.getElementById('count-gioi').innerText = soSinhVienGioi;
document.getElementById('count-null').innerText = soSinhVienChuaCoDiem;
document.getElementById('max-score').innerText = diemCaoNhat.toFixed(1);

// 2. Dùng vòng lặp for để render danh sách từng sinh viên
const container = document.getElementById('student-list');
let htmlContent = '';

for (let i = 0; i < students.length; i++) {
    let sv = students[i];
    let rank = xepLoaiHocLuc(sv.score);
    let rankColor = getColorByRank(rank);

    let scoreDisplay = sv.score !== null ? sv.score.toFixed(1) : '—';
    let subDisplay = sv.score !== null 
        ? `<span style="color: ${rankColor}; font-weight: 500;">${rank}</span>` 
        : `<span style="color: #9ca3af; font-style: italic;">Chưa có điểm</span>`;

    htmlContent += `
        <div class="student-card">
            <div class="student-info">
                <h4>${sv.name}</h4>
                <div class="sub-text">${subDisplay}</div>
            </div>
            <div class="student-score">${scoreDisplay}</div>
        </div>
    `;
}

container.innerHTML = htmlContent;