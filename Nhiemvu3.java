package model;

//Import lớp exceptions của Triệu
import exceptions.BookNotAvailableException;   
import exceptions.InvalidBorrowPeriodException; 

//Khai báo implements để hiện thực hóa interface của Phúc
public class SachGiaoTrinh implements IBorrowable, IStorable 
{
    private String maSach;     
    private String tenSach;    
    private int soLuongTon;   

    // Hàm khởi tạo để gán giá trị ban đầu cho sach giao trinh
    public SachGiaoTrinh(String maSach, String tenSach, int soLuongTon) 
    {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.soLuongTon = soLuongTon;
    }
//------------------------------------------------------------------------------
    // Kiểm tra xem sách có còn trong kho không (trả về true nếu > 0)
    @Override
    public boolean kiemTraSoLuong() 
    {
        return this.soLuongTon > 0;
    }
//------------------------------------------------------------------------------
    // Cập nhật lại số lượng kho (cộng thêm hoặc trừ bớt)
    @Override
    public void capNhatSoLuong(int soLuong) 
    {
        this.soLuongTon += soLuong;
    }
//------------------------------------------------------------------------------------------------------
    // Xử lý logic mượn sách và ném ngoại lệ nếu vi phạm điều kiện từ lớp exceptions của Triệu
    @Override
    public void muonSach(int days) throws BookNotAvailableException, InvalidBorrowPeriodException 
    {
        // Kiểm tra kho: nếu hết sạch sách -> Ném ngoại lệ BookNotAvailableException
        if (!kiemTraSoLuong()) 
        {
            throw new BookNotAvailableException("Sách giáo trình '" + tenSach + "' đã hết trong kho!");
        }
        
        
        // Kiểm tra thời gian mượn: nếu ngoài khoảng 1 - 30 ngày -> Ném InvalidBorrowPeriodException
        if (days <= 0 || days > 30) 
        {
            throw new InvalidBorrowPeriodException("Thời gian mượn sách giáo trình không hợp lệ (Phải từ 1 - 30 ngày).");
        }
        
        soLuongTon--; // Mượn thành công thì giảm kho đi 1 đơn vị
        System.out.println("Mượn thành công sách: " + tenSach + ". Số lượng còn lại: " + soLuongTon);
    }
//-----------------------------------------------------------------------------------------------------------------
    // Xử lý khi người dùng đem trả sách -> Tăng số lượng kho lên 1 đơn vị
    @Override
    public void traSach() 
    {
        soLuongTon++;
        System.out.println("Đã trả lại sách: " + tenSach + ". Số lượng kho hiện tại: " + soLuongTon);
    }
}