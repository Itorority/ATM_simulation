# ATM_simulation

# ** Chú Ý **

1. Hiện code để tương tác với database đã có thể dùng đc
2. Để phía UI dùng được phải có Controller đứng giữa làm trung gian .
3. Các ** Logic ** trong các chức năng người dùng trong lớp `UserService` khi tương tác với database a đã thực thi rồi nên chỉ cần gọi và dùng thôi .
4. Có 3 thư mục là [ entity , repo , service ] các bạn ** không ** được thay đổi code .
5. Để có thể dùng được code Tronng UserService .
6. Để dùng đc các function trong user service thì chỉ cần

```
UserService.getInstance().functionName();
```

7. File chạy lên đầu tiên là LoginForm.java ko cần phải chạy từ file chính
