# ATM_simulation

# ** Chú Ý **

1. Hiện code để tương tác với database đã có thể dùng đc
2. Để phía UI dùng được phải có Controller đứng giữa làm trung gian .
3. Các ** Logic ** trong các chức năng người dùng trong lớp `UserService` khi tương tác với database a đã thực thi rồi nên chỉ cần gọi và dùng thôi .
4. Có 3 thư mục là [ entity , repo , service ] các bạn ** không ** được thay đổi code .
5. Để có thể dùng được code Tronng service .

<hr/> 
##    ** Tronng lớp controller muốn dùng các chức năng phía Service **

```
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Controller {
  // use Userservice
@Autowired
private UserService service;

public void functionName(String email, String pass) {
  this.service.findByEmailAndPassword(email, pass);
}
}
```

## ** Tronng lớp phía UI muốn dùng các chức năng phía Controller **

```
public class UI {
Controller controller;

/**
 * @param controller
 */
public UI(ApplicationContext context) {
  this.controller = context.getBean(Controller.class);
}

public void functionName(String email, String pass) {
  this.controller.functionNameInController(email, pass);

  }
}

```

## ==> Các bạn code UI tạo 1 frame chính nhận vào `ApplicationContext context` từ `AtmSimulationApplication` khi khởi tạo UI chính và các frame con sẽ nhận từ frame chính để dùng lại.
