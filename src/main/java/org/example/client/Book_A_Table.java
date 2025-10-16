package org.example.client;
import java.util.Map;

record Table(int id, int restaurantId,Map<Integer,Boolean> slots){ }
class TableService
{

}
class BookingService
{
    public boolean bookTable(int tableId,int restaurantId,int startTime)
    {
        return true;
    }
}
public class Book_A_Table {
    static void main() {
    }
}
