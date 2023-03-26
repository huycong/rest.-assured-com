package com.congnh.rest.common.libraries.helper;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class TextHelper {
    public static String escapeNewLine(String txt) {
        return txt.replace("\n", "").replace("\r", "");
    }

    public static String escapeSpace(String txt) {
        return txt.replaceAll("(?<=\n)(\\s{1,})(?=\")", "")
                .replaceAll("(?<=\n)(\\s{1,})(?=])", "")
                .replaceAll("(?<=\n)(\\s{1,})(?=})", "");
//                  .replaceAll("(?<=\n)(.*?)(?={)", "");
    }

    public static String randomString(Integer len) {
        return RandomStringUtils.randomAlphanumeric(len);
    }

    public static String randomSentence() {
        String[] samples = new String[]{"Thành phố nào được gọi là Venice phương Bắc?","Khôi phục du lịch sau Covid-19","Thí điểm đón khách quốc tế trở lại sau Covid-19","Các quốc gia mở cửa du lịch giữa Covid-19","Du lịch trên những ngôi nhà di động","Đón khách VVIP đến Việt Nam","Một giáo viên nhiễm nCoV, 18 trường đóng cửa","Mất 28.000 USD vì mua tiền số ăn theo Squid Game","Nam sinh bị văng khỏi xe buýt, tử vong","Vinh danh 50 giáo viên có sáng kiến dạy học trong đại dịch","Bê bối biển thủ 1,4 triệu USD tiền từ thiện của hai nghệ sĩ nổi danh","Hai cán bộ quản lý thị trường bị bắt","Sao Ở nhà một mình diễn show Gucci","2,3 tỷ USD đổ vào chứng khoán trong một phiên","Trung Quốc thêm 93 ca Covid-19","Kido đăng ký mua hết cổ phần đấu giá của VOC","Lai Châu, Ninh Bình đón khách nội địa","Nghệ An phục hồi du lịch theo 3 giai đoạn","Ngày đầu Thái Lan đón khách không cách ly","Phú Quốc - từ làng chài nghèo đến thành phố đáng sống","5 địa phương sẽ cùng đón khách quốc tế trong tháng 11","Quán ốc nổi tiếng 20 năm ở Sài Gòn","Cô gái Việt chỉ cách sống ảo hiệu quả ở Rome","Vì sao cần nghe số lạ khi leo núi một mình?","Đi đâu trong nước tháng 11?","Thảo Cầm Viên hoạt động trở lại ngày 5/11","Những mùa hoa vắng khách du lịch","Quán cà phê chỉ cho khách ngồi 20 phút","Cách gọi các món trứng đúng chuẩn nhà hàng","Ba độc giả nhận giải ý tưởng hay cho du lịch Đà Nẵng","Vietravel tung loạt sản phẩm du lịch về miền Tây","Những lợi thế giúp Bình Thuận thu hút du khách hậu giãn cách","Quán ốc nổi tiếng 20 năm ở Sài Gòn                    5","Cô gái Việt chỉ cách sống ảo hiệu quả ở Rome                    1","Vì sao cần nghe số lạ khi leo núi một mình?                    5","Khách sạn ép khách không ngủ dịp Halloween","4 khu nghỉ dưỡng Việt có giá nghìn đô                    20","4 gợi ý cho kỳ nghỉ staycation tại TP HCM","Sắp diễn ra hội nghị trực tuyến ngành nhà hàng - ẩm thực","Hồ Chòm Núi - điểm cắm trại mới gần Hà Nội","Du khách phía nam đến Đà Lạt bắt đầu tăng","Thái Lan miễn cách ly với du khách Việt Nam","Thác Bản Giốc vào top đẹp nhất thế giới","Bốn khu rừng gần TP HCM","Những nơi ấm áp để du lịch trong tháng 11","Lá phong đỏ rợp trời thu Nhật Bản","Một tháng hồi sinh của du lịch Việt Nam","Saigontourist Group tung loạt ưu đãi quý IV","Yoko Onsen Quang Hanh hút khách với nhiều ưu đãi","Mùa thu Trung Quốc qua lăng kính khách Việt","Bến Tre, Long An đón khách TP HCM từ tháng 11","Đi leo núi, cắm trại một mình sau giãn cách","Du lịch Quảng Ninh thích ứng trong giai đoạn bình thường mới","3 phòng nghỉ kỳ lạ trên cây","Hoa dã quỳ đầu mùa ở Đà Lạt","Những chuyến du lịch khi vắng người"};
        Integer len = samples.length - 1;
        Integer idx = randomInt(0, len);
        return samples[idx];
    }

    public static int randomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public static String randomIdentityNumber() {
        return "456451" + randomInt(111111, 999999);
    }

    public static String randomPhoneNumber() {
        return "098" + randomInt(1111111, 9999999);
    }

    public static String randomName() {
        String[] lastName = {"Anh", "Vy", "Ngọc", "Nhi", "Hân", "Thư", "Linh", "Như", "Ngân", "Phương", "Thảo", "My", "Trân", "Quỳnh", "Nghi", "Trang", "Trâm", "An", "Thy", "Châu", "Trúc", "Uyên", "Yến", "Ý", "Tiên", "Mai", "Hà", "Vân", "Nguyên", "Hương", "Quyên", "Duyên", "Kim", "Trinh", "Thanh", "Tuyền", "Hằng", "Dương", "Chi", "Giang", "Tâm", "Lam", "Tú", "Ánh", "Hiền", "Khánh", "Minh", "Huyền", "Thùy", "Vi", "Ly", "Dung", "Nhung", "Phúc", "Lan", "Phụng", "Ân", "Thi", "Khanh", "Kỳ", "Nga", "Tường", "Thúy", "Mỹ", "Hoa", "Tuyết", "Lâm", "Thủy", "Đan", "Hạnh", "Xuân", "Oanh", "Mẫn", "Khuê", "Diệp", "Thương", "Nhiên", "Băng", "Hồng", "Bình", "Loan", "Thơ", "Phượng", "Mi", "Nhã", "Nguyệt", "Bích", "Đào", "Diễm", "Kiều", "Hiếu", "Di", "Liên", "Trà", "Tuệ", "Thắm", "Diệu", "Quân", "Nhàn", "Doanh"};
        String[] firstName = {"Nguyễn", "Trần", "Lê", "Phạm", "Hoàng", "Phan", "Vũ", "Đặng", "Bùi", "Đỗ", "Hồ", "Ngô", "Dương", "Lý", "An", "Anh", "Ao", "Ánh", "Ân", "Âu Dương", "Ấu", "Bá", "Bạc", "Bạch", "Bàn", "Bàng", "Bành", "Bảo", "Bế", "Bì", "Biện", "Bình", "Bồ", "Chriêng", "Ca", "Cà", "Cái", "Cai", "Cam", "Cảnh", "Cao", "Cáp", "Cát", "Cầm", "Cấn", "Chế", "Chiêm", "Chu/Châu", "Chung", "Chúng", "Chương", "Chử", "Cồ", "Cổ", "Công", "Cống", "Cung", "Cù", "Cự", "Dã", "Danh", "Diêm", "Diệp", "Doãn", "Duy", "Dư", "Đắc", "Đái", "Điều", "Đan", "Đàm", "Đào", "Đầu", "Đậu", "Đèo", "Điền", "Đình", "Đinh", "Điêu", "Đoàn", "Đoạn", "Đôn", "Đống", "Đồ", "Đồng", "Đổng", "Đới", "Đương", "Đường", "Đức", "Đăng", "Giả", "Giao", "Giang", "Giảng", "Giáp", "H'", "H'ma", "H'nia", "Hầu", "Hà", "Hạ", "Hàn", "Hàng", "Hán", "Hề", "Hi", "Hình", "Hoa", "Hoà", "Hồng", "Hùng", "Hứa", "Hướng", "Kông", "Kiểu", "Kha", "Khà", "Khương", "Khâu", "Khiếu", "Khoa", "Khổng", "Khu", "Khuất", "Khúc", "Khưu", "Kiều", "Kim", "Khai", "Lyly", "La", "Lã", "Lãnh", "Lạc", "Lại", "Lăng", "Lâm", "Lèng", "Lều", "Liên", "Liêu", "Liễu", "Linh", "Lò", "Lô", "Lỗ", "Lộ", "Luyện", "Lục", "Lư", "Lương", "Lường", "Lưu", "Lý", "Mùa", "Ma", "Mai", "Mang", "Mã", "Mạc", "Mạch", "Mạnh", "Mâu", "Mầu", "Mẫn", "Mộc", "Mục", "Ngạc", "Nhan", "Ninh", "Nhâm", "Ngân", "Nghiêm", "Nghị", "Ngọ", "Ngọc", "Ngôn", "Ngũ", "Ngụy", "Nhữ", "Nông", "Ong", "Ông", "Phi", "Phí", "Phó", "Phong", "Phù", "Phú", "Phùng", "Phương", "Quản", "Quán", "Quang", "Quàng", "Quảng", "Quách", "Quyền", "Sái", "Sầm", "Sơn", "Sử", "Sùng", "Tán", "Tào", "Tạ", "Tăng", "Tấn", "Tề", "Thang", "Thái", "Thành", "Thào", "Thạch", "Thân", "Thẩm", "Thập", "Thế", "Thi", "Thiều", "Thịnh", "Thoa", "Thôi", "Thục", "Tiêu", "Tiếp", "Tinh", "Tòng", "Tô", "Tôn", "Tôn Thất", "Tông", "Tống", "Trang", "Trác", "Trà", "Tri", "Triệu", "Trình", "Trịnh", "Trưng", "Trương", "Tuấn", "Từ", "Ty", "Uông", "Ung", "Ưng", "Ứng", "Vạn", "Văn", "Vi", "Viêm", "Viên", "Vòng", "Vương", "Vưu", "Xa", "Xung", "Yên"};
        int last_idx = lastName.length;
        int first_idx = firstName.length;
        return firstName[randomInt(0, first_idx - 1)] + " " + lastName[randomInt(0, last_idx - 1)];
    }


}
