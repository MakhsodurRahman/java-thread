package class_26_thread_group;


import java.util.concurrent.CountDownLatch;

class InvoiceBuilder{
    private final CountDownLatch latch = new CountDownLatch(2);
    private final ThreadGroup threadGroup = new ThreadGroup("invoice-thread-group");

    String user;
    String order;

    public void build(int userId){
       try {
           Thread fetchUser = new Thread(threadGroup,()->fetchUser(userId));
           Thread fetchOrder = new Thread(threadGroup,()->fetchOrder(userId));
           Thread fetchInvoice = new Thread(threadGroup,this::buildFinalInvoice);

           fetchUser.start();
           fetchOrder.start();
           fetchInvoice.start();
       } catch (Exception e){
          e.printStackTrace();
       }
    }

    private void buildFinalInvoice() {
        try {
            latch.await();
            System.out.printf("....final invoice ..... \n%s\n%s\n",user,order);
        }catch (InterruptedException e){
            throw  new RuntimeException();
        }
        catch (Exception e){
            threadGroup.interrupt();
        }
    }

    private void fetchUser(int id){
        try {

            user = UserService.getUserById(id);
            latch.countDown();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }catch (RuntimeException e){
            threadGroup.interrupt();
        }
    }

    private void fetchOrder(int id){
        try {
            order = OrderService.getOrder(id);
            latch.countDown();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }catch (Exception e){
            threadGroup.interrupt();
        }
    }
}

class UserService{
    static String getUserById(int id) throws InterruptedException {
//        if (1==1)
//            throw new RuntimeException();
        Thread.sleep(2000);
        return """
               {
                 "id":%d,
                 "name":"mak"
               }
               """.formatted(id);
    }
}
class OrderService{
    static String getOrder(int id) throws InterruptedException {
        Thread.sleep(5_000);
        return """
                {
                    "id":101,
                    "items":[
                        {
                         "item1": "info -1"
                        },
                        {
                         "item2": "info -2"
                        },
                        {
                         "item3": "info -3"
                        }
                    ]
                }
                """;
    }
}
public class Main {
    public static void main(String[] args) {
        /*ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        System.out.println(threadGroup);
        ThreadGroup parent = Thread.currentThread().getThreadGroup().getParent();
        System.out.println(parent);

        parent.list();*/
        InvoiceBuilder invoiceBuilder = new InvoiceBuilder();
        invoiceBuilder.build(101);
    }
}
