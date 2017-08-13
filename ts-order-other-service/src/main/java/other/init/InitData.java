package other.init;

import other.domain.Order;
import other.service.OrderOtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class InitData implements CommandLineRunner {
    @Autowired
    OrderOtherService service;

    public void run(String... args)throws Exception{
        Order order = new Order();
        order.setId(UUID.fromString("5ad7750b-a68b-49c0-a8c0-32776b067703"));
        order.setBoughtDate(new Date());
        order.setTravelDate(new Date("Sat Jul 29 00:00:00 GMT+0800 2017"));
        order.setTravelTime(new Date("Mon May 04 09:02:00 GMT+0800 2013"));
        order.setAccountId(UUID.fromString("4d2a46c7-71cb-4cf1-b5bb-b68406d9da6f"));
        order.setContactsName("Contacts_One");
        order.setDocumentType(1);
        order.setContactsDocumentNumber("DocumentNumber_One");
        order.setTrainNumber("Z1237");
        order.setCoachNumber(5);
        order.setSeatClass(2);
        order.setSeatNumber("FirstClass-30");
        order.setFrom("nanjing");
        order.setTo("shanghaihongqiao");
        order.setStatus(1);
        order.setPrice("2000.0");
        service.create(order);

        Order orderTwo = new Order();
        orderTwo.setId(UUID.fromString("8177ac5a-61ac-42f4-83f4-bd7b394d0531"));
        orderTwo.setBoughtDate(new Date());
        orderTwo.setTravelDate(new Date("Sat Jul 29 00:00:00 GMT+0800 2017"));
        orderTwo.setTravelTime(new Date("Mon May 04 09:01:00 GMT+0800 2013"));
        orderTwo.setAccountId(UUID.fromString("4d2a46c7-71cb-4cf1-b5bb-b68406d9da6f"));
        orderTwo.setContactsName("Contacts_One");
        orderTwo.setDocumentType(1);
        orderTwo.setContactsDocumentNumber("DocumentNumber_One");
        orderTwo.setTrainNumber("Z1234");
        orderTwo.setCoachNumber(5);
        orderTwo.setSeatClass(2);
        orderTwo.setSeatNumber("FirstClass-30");
        orderTwo.setFrom("shanghai");
        orderTwo.setTo("beijing");
        orderTwo.setStatus(1);
        orderTwo.setPrice("3000.0");
        service.create(orderTwo);

        Order orderThree = new Order();
        orderThree.setId(UUID.fromString("d3c91694-d5b8-424c-9974-e14c89226e49"));
        orderThree.setBoughtDate(new Date());
        orderThree.setTravelDate(new Date("Sat Jul 29 00:00:00 GMT+0800 2017"));
        orderThree.setTravelTime(new Date("Mon May 04 09:00:00 GMT+0800 2013"));
        orderThree.setAccountId(UUID.fromString("4d2a46c7-71cb-4cf1-b5bb-b68406d9da6f"));
        orderThree.setContactsName("Contacts_One");
        orderThree.setDocumentType(1);
        orderThree.setContactsDocumentNumber("DocumentNumber_One");
        orderThree.setTrainNumber("Z1235");
        orderThree.setCoachNumber(5);
        orderThree.setSeatClass(2);
        orderThree.setSeatNumber("FirstClass-30");
        orderThree.setFrom("shanghai");
        orderThree.setTo("beijing");
        orderThree.setStatus(1);
        orderThree.setPrice("1000.0");
        service.create(orderThree);
    }

}
