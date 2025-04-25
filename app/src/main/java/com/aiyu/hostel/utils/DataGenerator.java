package com.aiyu.hostel.utils;

import com.aiyu.hostel.core.data.Amenity;
import com.aiyu.hostel.core.data.FoodItem;
import com.aiyu.hostel.core.data.Hostel;
import com.aiyu.hostel.core.data.Priority;
import com.aiyu.hostel.core.data.Ticket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataGenerator {
    public static List<Hostel> getHostels() {
        List<Hostel> hostels = new ArrayList<>();

        // Common amenities sets
        List<Amenity> basicAmenities = new ArrayList<>(Arrays.asList(
                Amenity.WIFI, Amenity.LAUNDRY));

        List<Amenity> premiumAmenities = new ArrayList<>(Arrays.asList(
                Amenity.WIFI, Amenity.AC, Amenity.FOOD,
                Amenity.LAUNDRY, Amenity.GYM, Amenity.STUDY_ROOM));

        List<Amenity> studentAmenities = new ArrayList<>(Arrays.asList(
                Amenity.WIFI, Amenity.STUDY_ROOM, Amenity.FOOD));

        // Common room options
        List<Hostel.RoomOption> sharedRoomOptions = List.of(
                new Hostel.RoomOption("4-Shared", "₹8,000/month", "Shared room with 4 beds", true,
                        List.of("Single bed", "Study table", "Wardrobe")),
                new Hostel.RoomOption("3-Shared", "₹10,000/month", "Shared room with 3 beds", true,
                        List.of("Single bed", "Study table", "Wardrobe", "AC"))
        );

        List<Hostel.RoomOption> privateRoomOptions = List.of(
                new Hostel.RoomOption("Single", "₹15,000/month", "Private room", true,
                        List.of("Double bed", "Study table", "Wardrobe", "AC", "Attached bathroom")),
                new Hostel.RoomOption("Double", "₹12,000/month", "Shared room for 2", true,
                        List.of("Single bed", "Study table", "Wardrobe", "AC"))
        );

        // Common policies
        List<String> commonPolicies = List.of(
                "Check-in time: 12 PM",
                "Check-out time: 10 AM",
                "No smoking in rooms",
                "Visitors allowed only in common areas"
        );

        // Mumbai hostels (10)
        hostels.add(new Hostel("H001", "Urban Stay Mumbai", "Andheri East, Mumbai", 4.2f, 128,
                "₹8,000 onwards",
                List.of("data:image/jpeg;base64,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"
                        , "data:image/jpeg;base64,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"),
                basicAmenities, "Comfortable stay in heart of Mumbai", commonPolicies,
                sharedRoomOptions, 12, "contact@urbanstaymumbai.com"));

        hostels.add(new Hostel("H002", "Luxury PGs Mumbai", "Bandra West, Mumbai", 4.5f, 215,
                "₹12,000 onwards",
                List.of("data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBwgHBgkIBwgKCgkLDRYPDQwMDRsUFRAWIB0iIiAdHx8kKDQsJCYxJx8fLT0tMTU3Ojo6Iys/RD84QzQ5OjcBCgoKDQwNGg8PGjclHyU3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3N//AABEIAJQAzQMBIgACEQEDEQH/xAAcAAACAgMBAQAAAAAAAAAAAAAEBgUHAAIDAQj/xABNEAACAQMCAwMKAgYECQ0AAAABAgMABBEFIQYSMRNBUQcUIjJhcYGRobHB0RUjM0JichYkUvBDU1Vjc4KTsuElNDU2VIOSorPCw9Lx/8QAGgEAAgMBAQAAAAAAAAAAAAAAAgMAAQQFBv/EACURAAICAQQCAgMBAQAAAAAAAAABAhEDBBIhMUFREzIFImFCM//aAAwDAQACEQMRAD8At7SVBtNx+8aH1SOI3KCR+TMZAPNjfO1DcHa5aazazLaiVHhYF0lXBUNnG/Q9DRusYE1pkZ9M5A91BKmg1aYA+imSNJYD2bMA2UO/y765cksJ5J98d+MZpjtB/VYu/wBBftQeroD2LEDIaqarkilbI1ScDB2rsuGHTFava9hMxDkq3QHurog7qtEZpMUijaSVlREUszMcAAd9IN/5WeHbaQLaxXl4NxzQxhR/5iM0f5XdUfTOEJoomAe9cW+e8qd2A+ANUJHZ3MtsZ44XaEHBKjPfiibSIk30fSHC3F2j8Thxp05E0e7wSryuB447x7aY1WvlLTrq/wBJvIryyllt54zlXU7+0e0eINfSvCmsS6zw9YahKqrJPEGcDpzd9RMppk2ErYLXHtW/tV4Xc95qwQjlrOUeIobLHxPxrXDeBqECiFHeKznQD1hQ3K5GyZ+Fe9lIf3ahDqZk9prztVPQGtBC/h8M1hi8SPnUZaPWkHhWvaE9AK8Maj975VqygdM0O4ujbmY9wFanmPfW+NqzFECcSD31oy13IrUrULB+TcV2UbV4V9IV0A2qFCr5ITvq2+cdj/8AJTnqO9/b+HK32NJ3ki9TVf8Auf8A303arJ2d7a+jnmyvXx2pcfohs/uyQsh/U4R/m1+1C6vskZ/jAoqx/wCaQf6NftQ+rAGJcjOHB+tE+hfkHvRhxQcrYXGSO/Y0XqDqJAGdUVRuzHAqOu2IJQEZIOCPdSpS8DEiG4pittR4YuYtQ5H5XUekoJUk4yPA42yKSbQx2sZjsohFbqvXlyq+GT3fGm7i6Hm0rUWGyxRiUgdcLuT8s1JcE2tvZaYtkFYySxLNMH39IjcfbakbXOVNmrHkWOFpclc65wpreqQReYaeCt03O8/KFXk27uoJ92+OtWnw9pS6Jolppyv2q26lVbGMjJI+9SrDqO7wr3Hqnv8AGtcYbVSMuTI5ytmqlcZwuB1ya7RxM6gqE36Z762VImeRZYfRZsYYdfS6+6iGwgJHor7BS90u30LOHm8viinxxQWsXaaTYy3czO4jXmKRruaK84ZHdm6Lnr3j2fWgOMXjbhbUGDDBi/EUDz7otplx5YJc68sUEMqWl23atyjmXlBOO7PWor+l7s5RLFy/KW5cjO3Xv3r3iW+sJNOhNhNzHnDgKjcvMBsw267dBQtrcWccqmWCdEXmZOS2k/WA7g+rv39/3rNm1GWNbS8nFUgyXXdXXkJsbcJJjlYyZB6+APhQuq8Rahp2qGKXzeaPzRpeS3BbDBh1JHTGaE87mgmxLFd3PpnDyR8pBJztzH5VF3Wp2l/rEjyQSJALR4yGPpg8w3GCQSN9ulBDNlm+w8dOVNDHoOo6lqOoW3b336ub0zFCoARSCQMld+mOtNLrjbwqvuFNTe+1vS44cQ2kWwjwcn0SMk43PfmrFkArZjhKP2dlOrNQKzFeisrUKNDXhrY14QfCoQ5N6wrcdK5ucHfb316JYQMNMgP8wqFiV5M7s2gvgHwX7LqNv3qd78G8e3ZkUCNw3f457u+q94FVj52FIweQHI/mqwoiBAqODlcYAOc0vHzEZl4mS9ptbRA9QgB9+KH1X9iu2fSH3FCrMVbmjblP3re5uVmjAYjP0o2uBafIjeUSWKfzaCRWdeYyNH3bbDI76G4c1VFiihvC0Mdup5QUJyAcqOh2AxvXXj+1Qeb3zXcVvsU5ZFZubvGAKVUuY4bFQWJEgBdmPX3eArnzbUmzpY4xnjUWN8/ENqWuCLcukq8gD9CuN8jw6/Ogm4pe3upJluVWZ+oCgqfgaT5dXaZQCwIHqgDORWX/ABZcGyksJEgBkf1RGGZgQMZ2+NSO6TsKSx4+lYxTavqWvalAsmqTWyQNzdpaYyv+qCVJ7t81Z9ncxXUEbRNnl2YHYj3iqg4Ztp2gjco4UYbfqT/wp20u/a0nEvMoRW5Gz0Yd9aYSa4ZhypN8IeJN228V+9cXCMzNll8Tg75pY4v4ztNP0+OXRtQ065uWmVGjEokIXfJwrZ6gU2WEpvNPtrhwA0sSSbdASAauUN/QtppAcsEglDAso6Ltt4YPWoPiuOW24d1NZ2Zw0YYHqN2Hy6fWmV3dWwsYO5zg9KheNJxJwjqfLg4jHo4yfWXqKzrSxvhkVrkidfaxiht1tLlOxkcHsRL6CtynDDwGcZHTepSxvNIvNGtIL64tnCJykM4zlTgVG8T6bDDp0QtGEQ5+ZEUDCuFJUjvG+NuldOFr0pbXERlCxGYtECwGFYZHX25o1g42vot8oHv4rAyMtreho9iqqrsFI6bAezrStctZDWrpVuEceYyo5kUx4kyPR9IDerEudSt4tpL3lPtuVX6cwqnOJpVk1/UHjkDK07EMDzcw9/fQQ0WOE9yBglYZpV1JZSRzWLiOdSAjYDYJOM77dCetdtI4u4iujcLcaw7GNgB+piX2dyVAaw0kWi3bQMVcKpUqcEHI6Ux+T/StP/pHqWm6mkd32USmMyA+kQRk/EMK1c+BtRXYZNruslVxqrb9cOB9loGTWtUlJEmr3KnwWZx9qsheHNAXppNmT7YQa3GiaOnqaZaL7oF/KjqQG6BUk2oXhLZ1a8cd4Ny340I945RVku5S2epuCfnvV0jTtPX1bK3HuiWvfNbZfVtoAf8ARih2SZayQXgo2WU8+BKzDx5ya0UK2TyOfep/Kr1MSL6scY9yCtlkVRjs1+VV8TC+ZehD8n2eS5yOpT8aeZJp1VIoApdt8t0UVRbXN0l1GlrdS2+UYkxkjOMdfnR8Woa+q9rDrMzJ0w1BHLtjQcsO+V2XQsdwf2jI/wDLtW3ZsScxkY8DmqVbWtdRMtdk+3mIrmnEHEkTfqbtQPDnP41azp+CPSy8MuDUtHs9TVRqFos/KMKZF3HuIwRUBfcB6Rc2/YRm6t13x2UuQM/z81JcXHHFVvygvbzKOoDDb4nFHR+UrWVA7W1tH365B+zVN+N+CLFlj0zpqPk81CJVGmXkU4UerOpQn2ZGRUDfaFqdgzzT2Mts5TlN0I+cKBtswyB8amx5VLvJEunQeicHr+dd4fKbIU5p9PkdSP8ABkAY9tVUK/Uu8n+lYq6Fqi6f2sT3jNYBgxCNkxknHxGe4eNTugX03FOrfoq1uGsbJIi8sxXMpB2wCdlO/Tc17c8VcPXylZdBKs5yeyPLv4jHfRWkcZaVYWa2q29yRzHMjqpY+/lAq415BlHykcOMOBtJ4Y0P9JWF/d3EonjRo5SnLhifAZ+tWxwjfwXPDemss0fN5tGHXmGVYKMg1TPG3FFrq9jBa2IfCy80nOMEeGKm9E4t0iDSrWC5vHE0SBXUxscGiVKRJKUo/wBLBv8AT5xqMt3Z300AdRtFMpUn2q2xpc1/9Ly6VqNoNPEzzR/tYIyrMQRjYEgnbuxQS8ZaH/2p/wDZNXVeM9Ex+2lI9kRoNsLvcDU6pxF3UuEJIdIgurK21i4vWXM1vKRhXOOmSNvcdqW30fWLSFp9U0pbaFd+aRcAn/xVZH9MtG5fXkbw/VkUtceajYa5pX9XMnbw5KjsWAIOM9/s2ontrhkUZJ8oQbjV0RSILWEZPrcuSPnXI67KRuFwO4LijtR0YQcOWt2hHO6KzhTkkn+4pSdiFO56VIfsFkTgWdZpDeS28U5AheSMsTttzA0wR3drYeVLzgXMS29zDhpOcFR+r8feg+dKK3CWlnHNKSqIIyWI27qGkuBPf288eXVyACo2b3UV0BtsvJ+INEj2fVbIN3gTqT9DXL+lGg4/6Vtz7iT+FVhBaXvrLp1469doTjFEi01IrhdNuyD0AjFXvYPxL2WGeKtA/wArW+fA5/KtG4r0H/KkH1/Kq3bSNYZx/wAlXm/+brDomsscjRrsj+UfnU3v0RYo+ywpOMeHVO+qwk/wqx/Ch2414dz/ANJL/s3/ACquZeHtdU836FuRnvwuPvQzaHrhJA0qXION2X86rfL0EsUfZHKzG5Uk5/VN91ryZ9ZWTFkjmHGRiPIJraPHnqA/4p/utM+njFsg99Z4K2aZOkKjPxLIAPNiR7YgPxrwW/EZ28wz8B/9qeFrulN2IDexHjD20am7H9Z740G467de6hYGM16lnBHKbqVlwG5QNzgb5qS1kf8AK7AeLfjQWjf9crI/xJ/6goIpUw5Sdol28n3EFzIT2duiNjP68Z+1ESeTvWLW1aa4ntQkMZJAYn0R7BVrQEDJJ6daU+KeIbm6Q2GmIUQqwmcrv3Y5abtikK3uxM07hsX8AlFxFGueX0kcY+tMll5LeaGK5XUocTHGViY9/wDNSja2DTTyxXUslwzHnjKOTjBwfZnJ+lP/AAVcz288NlNOzTbcyt3jNKuCYac2rRD8ccBDhnh6bU4tR7d4mjHJ2JAIZwvXnPjShpmltc6vBZB+zE8yJ2mAcc2Og76uPyxsrcDX+CMgwAjPQ9slV5w7ZXx1/TZTpd08L3FuUuEtnKqoK8zc/TGxqTXKSLxze1tsD480NOD54Izcvd9q7L+zCYwAfE+NCXlt5parN2jOpTnIC4wMZp78rekXGqXSSQ208qws5AihZy+QBgcoNQvFWm3Vlw5FCtvMQ1skszLG2F9F9mOO7A+lVOr4QKyT2W2QNhAb2S0jVnBnKhcAHr8RQt/ey2sxgdXwQcjONwcEU02UFxp+m8M3Bs25+0Mi9/ajA6Y9mT40ka858/WSRDKHMg2OM+lv3HxqR5nQPySfZJWesQwab5obcuxY8gIBUChNQ4aikthPDIqPjnbbr34qc0TS4oLFJViCzXCCR2bc792fDpW+sydjp0xAAxG2w6Cs09Qlk2wOvh0Mp4N83xTZGzxed6ctt20cIdVPO+wGBnrRFvw5bR8K6dqxeRnvLvs3BYjlTmK469cqd9utRsq9rZRx59ZVH0py0/s7jya2cCOO1tLs9oPAiUv9VNbW+aOQlxYGvDemQjmhS4Rj1IuXz96FbhuyR2kgM8Up37RJSGz7+tMvZ7Vykjpm1UBuZAWVjc2E4kTVdRZB1i85cK3vwa91jzq+MZGpajByAjEd2+G9+TUrJHQNwlSiWxR1c+ZqvnLzXxZs5upmPJjw39tTLcHQXeJGupQMDChAVX3ZqF4sX0E/1vwqxtPGYFx/ZH2quC7aEdR/X0H+bb7imfT97VT7T96V3bkvFY/4tvutNGknmsI28c/ekQ7HT6DFFdkrl0rYyJGvNIyqPacZpwoUNRPPrDL4M+/zoDTHC8W2be0f71G3DCTW2ZCGRmfBHxqKifs+JbZj3fnQQ8hz7ReVzOsVlIWBYYNIOqTusxcMwPMCT4kDP3xTnaXcUmVfBU7HNK+oIsM7Qc6ychPK6nOfCqyuqZIq2QXCcMq3F8zhmnWQhYw/cTn8actDXs9bs5p5YxIRgqMDk8c5Pw9+aUr2G7V2vYYWjhbKjkQ5694AyDR8SSTaN2s6mMRKXVWPf47+ykU29xoi4qG0dvK48D8C6q8SAZaAlhj0/wBcm9R3C+j2stpot/JFcvOiowIUFVwc9T3bd1QGq6q975ONZs5Z2c2/m7wksSChlXI+H41YfAqK/B2lZdhmDoDt1NaIx300ZZP400L/ABzp8mpSxQRSXdownPNNFZNKoyD15WBx7Rmttc0+yvNAvQj6lLe2ViVAEMiK7qrAbEelnPTJ7qfOwz0k+g/Ks82PdJ9B+VG8TuxKyKqZW8Lw3FnwpbSRXLNaEi4iSIqy4Q9+2/xpBn0vzyzScp2kPnhg5ApLR5CksT0xg+Odq+h0tiG9JyRkdwqpeF7zzbhG4iDHM2oPk/wqkefuKCUXFWOxv5HSOcqLGywRLuqgBV7qi9esLo2JV4ynaOke/wDEwH40xTTvb6FPqsCpyK4iMrf2jtsPzpEv9b1CbHPeiTkkDrzLjJGa50cKWTcz0az5Jadxxrjo0AAvUgJ9FZCufdkUw8PehFqkSDmgKK7EdAwz9cfalS5maNlmGO19bOO89ab9F163i4KaGUZmi5+0A9aRmY4+jD5Vu7mjiODWKT8DEADGpHQgH6VwkAqJ4f4ht76xSOR1SeECNgT62BsR8KPluU6Aj271qMSOcuKAuQK7y3K0HNMpqrLFPiz1E+P4VYmlHNqp9g+1V1xUQyRY7yw+1WHo29muf7I+1Ci2I116VwoH+Kb7rR1tq1zFCtvGqKF/fK5oR1zeRgnC8jZ+a1PabawG1WRowWJOSffWSCbao1TaS5Iua7vp/RjuZ3bwiXl+1ew6bdSnnnT4ytk1PMwjGFwF9gxQs0+M71o2v2Z3JeCBCFL9O4Asu3TYEUu6vO8Op9pHsyjY/GmWQNJfqEGWLPgfA0O/CzXdx21zNhT+4nX51WPpl5O0RD8RXVzKDeSXDR96Ry8opz4ceG7tnvoMxKqjli5yQCOu3fvnNRX9DbY+rLOvuYGpm30/9CwW0EXMySRtknqWz1PwIoc6/TgZga38j9p88Go6bFOkaIsgJZANge+uDano/D8yXmrtGlqcxnMZf1h3BQT7OnQmofRdWjsdNNvyMZwxwAPGk/izTNZ1q/LmeI26HMcTMQFJ9wO9FB8C5p20Sl3+iNQF7ZaTfJcWTj9W6KysFJyAykA5BA6+Aq1OCLKBuFdOSO7bnji5HCsMBgTnavnj9G6xoUgvJLdliUgF0YFSPDx+YqxuGdQvVs4r6yW5jWTOTEc7+1f+GKpN423XAbissEr5Rb36OkB9G5PxWs8zul9WWNveCKT7XjWRMJepzH+0Byn5GpiDia2nUFJQD4E4+9OjljIzSwzj4JfsL1TuIiO/DVRdm8llw7zN2g5nZ/RXmI5sDIHfsoq3r/iF7e1aSBedxsBnI36ZpK0HU4NJvfOXjVreGAjC42zj+/xpWZp0rNOlhJXJdkPr/E+l6tw5b6dpEFzbw2rjnWeILzeiR1BOTnf50k3LRRqqxkBz4DNM2sak+rX813dgAyHIUDCqvcKT5JQ93Mw6FtvdSMDWXI5eDpa1T0emhjvl8s9nYHBck8p7hXW5imt7YLn0ZASB4nqKHkwilm3ODha6XE7uYgzkhQSBWxQinZx3nybHG+GTPk8i0+TXI7HVlPZXKnlIkK4cAsM49gP0qyNQ0zh21yscXM3snkO/zql7CVoLntyx5opQ3y7qtSKGd4FmS1lMTgMrBScjFGKiava6MwwbeZD4pM341Gajo6PGW067cNg4WUZGfhUqYzjPm8uP9GfyrQkDOYZcAZ2jNC0w0VLqF1czO8N5GEkgJDLjGDkVa+hOGsUP8I+1QfEmgQaw4aFOyueUhpsYz4A+NG6C00MDwzApLGQGU91UkRsWn2uI2/hYfVanrCQCyT3n70uO4M8Y9h/CmDTY2ezRghIydwPbWTF9jVm+h0dmZsCvFt+Y5eiUi9LLAijYYk2O59gGa10ZbFaBCdehj7ud/s1NEduNtqXbQKeL4hklRNJkD+RjTvHHCxGBIPlS8S4YzM6aA44o48dpgA9M1rxBb8tpHOhyI265232P4VJvbMShibIB3DLn5URHaJNA8My5VlKkZyMH47VMivgrFKnYs6SyTXRTlDM6gqD7OtMMNp2qLG8QRkAZsnOT3UoSRyaTfGCV3ilUns5u5h3GmnQr9DCVmmLTseZif3vdjupMHXDNOWN/siJ4z0y4udDvI0UhimVx0yCGx9K04BuEHDdsCRuW3+NOayIwKnlIPUbUs8TcKm808x6LcmxfLN2Kjlicnc5xuD9PEU+MvZllHglBfWMsggeaFnP+DLAsPhXkmmWrQzC3zAzZOFOVz/KdvliqNntrnTbt45kaO6gfDE9VP99wasXhHi2a4tBbXMIknh25mkAaQeP9/CjcVIWpSiN+lwG209u3iha4TJXlXGT1FVpp010NIjhnb1yHOepwMDenPVdZvZLaRYtPKry47TtCAOu/SlRXULyhdgMD2Vh1s9kVFHe/C4Plm8svALqMnJZvygqSMbdKXk9ZmPeaYrhFdSpzg9cVAxwkoGDggk7YqaKcYxdjPzWmy5M0XBWqNGHO2/hW0UbBw7DCkEDPfWtuyzXSwqwDsSo5hgfOpdbUX7iK2ZU7L1jK+3w2roWec2tcMB03T7i+v2gtYjIzkdOg9pPcMCrf0y2lstPt7XteYRxquebrgVEcMQafo2m9lcXkZnlcu7DGPYB39BTBBeWTDmS6R1/tc42+tXfolV2eOrYB5mI6Yx0r1FAB2APuoqOaGRW/WqRjqDSFxjx4sAt4uG9QtJpw7CY8nMMAbYJ2Pf0qcl8DkIhnJUMPd1oe70u2mKO6MdtmTbPvrOEtWfWNDtrybsxO6kTBFwAwPQZqZBI6VCdoo+4A7ROXvFWPwXAj6LbO5GTnv9tVpKCyL76NttQ1Ka3j022aV0HSOLbOeucd1YMclGRuyw3Q4LM1niTQdMjeOQLd3A27GIBuU+BPQVX9/rV7rVw0Vlbebo+yQW4YnHtP/wCD2URa8NdmEbVZirH1LW33Y+wnuHupo0vRpIY1CKtnD3RxjLN7zT7lP+Iz1CH9Yo8O27wcSWsU45ZElkUrnO4jbP1qzrS1MvQcoz3UgwKsfHVvHg/84lG/X1Gqz7VDJGCDyovU1eHyVn5pnggjRe4keNaSwDHNFjm+9R+s8QWGlobi5f0FyIYhu8r9+PZ7arDiDj3V74OsUws4yf2cPXHtbv8ApRykugIY5MtG6hs71fNr6FX8Aw3FRNzwxJDltPn5069lMcEe40v+TLV3u9MksdRkEhilPYF2y+MZI8e+n6Idnsr8y+DdRSZQTHxyNdCwl5dWM/m86uHA9VuuPZ4ipqxvFuoeZSTuQyk4Ix40LxlGx0rzuMZltm5s/wAPePx+FQXDFxMzSSvnkkXI260KUk6fQcnGUb6Yu+UpBFrUcqjlaW3Viw7yCR+VJN4EwveefcEdKfPKqrJe6bJgkGF0PvBU/nVez8xkZyNiOb4itUDHPsIt7ma2ZXhkZSvQA7fKm21ueezSWXHMRuw6fKk1TkCmXQJBJYMpGWjyv9/nS9XhjOKbNn43WZdPJqLDHkDRsyENgdRUZY+mIkUDCAs3zOKi/wBITWomliK+lcFWUj0TtR2hTC5M8xUIV9EKOgFZVp9ifo6Wb8ks9VxLlATxhnGdskmjJAURWjdlJPVTjNaQDM0Xvo0JvggEVby7U0zJ8G6mkaw395AihZ5AB/EaKGsXpGWlDfzb/c1xEKnuFeebxkMQBtSlqGOemgwldYvInWRW3G4wo2+lATvbSSLNHZWtvJ3mBMZ96klfpW3ZAj0SR7K5PF/N8xRrPP2A9Lj9Ejwrq6cLTSzW1v27Spyt2jlRj3Abn200p5SgR6elb/wT7f7tIRTb1vpXMx/3xRrNL2C9Lj9Egm4UHwP2NNejt5tpkRgARpHYMwG/WsrKqH2QjL9WNuk2sUSJIo/WP6zncn41PPCkVuWUekR1NZWVsiYpFZ25MnHcHPv/AFuT/danfi/UJ9O0iTzUquIS2464FeVlLh/odk7iUvqF1PdSNLPIzvtjJ6DwqNk/Zl+pHjXtZQw+w3JxE46JdTW2s2NxE5EvbKOb2E4P3r6Dt3ZlBJrKym5DNjCZUWa2kjkGVZSCPEUr6KAlqFUbA8o92cVlZShqIXyqxJJYWEjD0llbHyqt/VjLAD0WDYxkEjxrKyn4+hE/sOdtDZTW3NLptkzEbnssH6V5Da20Ha+bQLCpXLKhOD8yaysoMj/UPCv3Qu63p8Npaw9mXPaydo3Me8qK80EBROB05QfvWVlVP6hY/wDoe237aP31JY6/GsrK5ubs7GH6mL0rUeq1ZWUoYc06isI2X+X8ayspgJz5RXNgM1lZRAM//9k="),
                premiumAmenities, "Premium accommodation with all amenities", commonPolicies,
                privateRoomOptions, 8, "+91 9876543210"));

        hostels.add(new Hostel("H003", "Student Hub Mumbai", "Powai, Mumbai", 3.9f, 87,
                "₹7,500 onwards",
                List.of("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQYxP1dxLzA-CwMkwtAzNHEUxv5k9RKzYyXYPPxsS48XJOdC33xkVY66LuTIXA68RZ0RS8&usqp=CAU",
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRCDWU3dicvxtoEvCdxhYVjlb3r8akoxaftnZTw2YpxnvjcXjB_3GSZbR-d5Yy1oSgA2Vo&usqp=CAU"),
                studentAmenities, "Affordable stay for students near colleges", commonPolicies,
                sharedRoomOptions, 20, "manager@studenthubmumbai.com"));

        hostels.add(new Hostel("H004", "Cozy Nest Hostel", "Vashi, Navi Mumbai", 4.1f, 95,
                "₹9,000 onwards",
                List.of("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQKH34vxF-PyrGtoF0xxuySxCQWeglzyPuws5PU_p5oOHGAOK-Fjj9D8dPbTBYaN5HfUPI&usqp=CAU"),
                basicAmenities, "Peaceful stay in Navi Mumbai", commonPolicies,
                sharedRoomOptions, 15, "+91 8765432109"));

        hostels.add(new Hostel("H005", "Metro Living", "Ghatkopar, Mumbai", 4.3f, 142,
                "₹11,000 onwards",
                List.of("https://www.go.study/assets/canada/student-accommodation-in-canada.jpg",
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSapl0JibBpWXa7UNjZ0zxAn4zswVmNn3E6rRJILoTFKVT2qj8LmlZHoTdjJ8yr00y1Suo&usqp=CAU"),
                premiumAmenities, "Modern living spaces with great connectivity", commonPolicies,
                privateRoomOptions, 6, "info@metroliving.com"));

        hostels.add(new Hostel("H006", "Budget Stay Mumbai", "Dadar West, Mumbai", 3.7f, 76,
                "₹6,500 onwards",
                List.of("https://4.imimg.com/data4/LR/LY/IMOB-35737786/img-20171228-wa0006-jpg-500x500.jpg"),
                basicAmenities, "Economical option in central Mumbai", commonPolicies,
                sharedRoomOptions, 18, "+91 7654321098"));

        hostels.add(new Hostel("H007", "Girls Hostel Mumbai", "Malad West, Mumbai", 4.4f, 167,
                "₹9,500 onwards",
                List.of("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQPHwS8mLlGd0fMHtJn6a-9lJ7FXQYpGrf3wg&s"),
                new ArrayList<>(Arrays.asList(Amenity.WIFI, Amenity.FOOD, Amenity.LAUNDRY, Amenity.GYM)),
                "Safe and secure girls hostel with all facilities", commonPolicies,
                sharedRoomOptions, 10, "girlshostelmum@gmail.com"));

        hostels.add(new Hostel("H008", "Mumbai Backpackers", "Colaba, Mumbai", 4.6f, 231,
                "₹8,500 onwards",
                List.of("https://www.google.com/imgres?q=student%20hostel%20images&imgurl=https%3A%2F%2Fcf.bstatic.com%2Fxdata%2Fimages%2Fhotel%2Fmax1024x768%2F586173369.jpg%3Fk%3D46499c7f07cf274cc1cafd7b76f3c129ee46d4169993c46aaf05eb6ab3028e27%26o%3D%26hp%3D1&imgrefurl=https%3A%2F%2Fwww.booking.com%2Fhotel%2Fgb%2Fglasgow-youth-hostel.en-gb.html&docid=0pVXFO_Ge74Q-M&tbnid=yMO4VNde7-5JJM&vet=12ahUKEwjL4Nep9vCMAxXm4TgGHQOoNYI4ChAzegQIZBAA..i&w=1024&h=681&hcb=2&ved=2ahUKEwjL4Nep9vCMAxXm4TgGHQOoNYI4ChAzegQIZBAA",
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSLT7W9pbRgXciQvVIsQEE1I6HQaOx5Utdn8g&s"),
                new ArrayList<>(Arrays.asList(Amenity.WIFI, Amenity.LAUNDRY, Amenity.PARKING)),
                "Best hostel for travelers and backpackers", commonPolicies,
                sharedRoomOptions, 25, "book@mumbaibackpackers.com"));

        hostels.add(new Hostel("H009", "Corporate Stay", "Lower Parel, Mumbai", 4.2f, 112,
                "₹14,000 onwards",
                List.of("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQTFqUACdQ5LkKW8um03UTzDPwG0zlOh4OP9A&s"),
                premiumAmenities, "Premium stay for working professionals", commonPolicies,
                privateRoomOptions, 5, "corporate@stay.com"));

        hostels.add(new Hostel("H010", "Sea View Hostel", "Marine Drive, Mumbai", 4.7f, 198,
                "₹13,000 onwards",
                List.of("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRGvpP0ThbpRcLRedqGQNY2flvzeaEbLJSwlw&s",
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSFPJtTW4beDiaNaSunb1OeMLHR9qIv55JEnA&s"),
                premiumAmenities, "Hostel with beautiful sea view", commonPolicies,
                privateRoomOptions, 7, "seaview@hostel.com"));

        // Pune hostels (10)
        hostels.add(new Hostel("H011", "Pune Student Hostel", "Kothrud, Pune", 4.0f, 105,
                "₹7,000 onwards",
                List.of("https://example.com/hostel11/img1.jpg"),
                studentAmenities, "Popular among college students", commonPolicies,
                sharedRoomOptions, 22, "+91 6543210987"));

        hostels.add(new Hostel("H012", "Tech Park Stay", "Hinjewadi, Pune", 4.3f, 134,
                "₹9,500 onwards",
                List.of("https://example.com/hostel12/img1.jpg", "https://example.com/hostel12/img2.jpg"),
                premiumAmenities, "Close to IT parks with shuttle service", commonPolicies,
                privateRoomOptions, 9, "techparkstay@gmail.com"));

        hostels.add(new Hostel("H013", "Green Valley Hostel", "Baner, Pune", 4.1f, 89,
                "₹8,500 onwards",
                List.of("https://example.com/hostel13/img1.jpg"),
                basicAmenities, "Eco-friendly hostel in peaceful location", commonPolicies,
                sharedRoomOptions, 14, "+91 9432109876"));

        hostels.add(new Hostel("H014", "Girls PG Pune", "Viman Nagar, Pune", 4.5f, 176,
                "₹10,000 onwards",
                List.of("https://example.com/hostel14/img1.jpg"),
                new ArrayList<>(Arrays.asList(Amenity.WIFI, Amenity.AC, Amenity.FOOD, Amenity.LAUNDRY)),
                "Exclusive girls hostel with strict security", commonPolicies,
                privateRoomOptions, 8, "girlspgpune@example.com"));

        hostels.add(new Hostel("H015", "Pune Backpackers", "Koregaon Park, Pune", 4.4f, 152,
                "₹7,500 onwards",
                List.of("https://example.com/hostel15/img1.jpg", "https://example.com/hostel15/img2.jpg"),
                basicAmenities, "Vibrant hostel in party area of Pune", commonPolicies,
                sharedRoomOptions, 20, "book@punebackpackers.com"));

        hostels.add(new Hostel("H016", "Executive Stay Pune", "Aundh, Pune", 4.2f, 97,
                "₹12,000 onwards",
                List.of("https://example.com/hostel16/img1.jpg"),
                premiumAmenities, "Luxury stay for corporate professionals", commonPolicies,
                privateRoomOptions, 6, "executive@pgnest.com"));

        hostels.add(new Hostel("H017", "Budget PG Pune", "Pimpri, Pune", 3.8f, 68,
                "₹6,000 onwards",
                List.of("https://example.com/hostel17/img1.jpg"),
                basicAmenities, "Most economical option in Pune", commonPolicies,
                sharedRoomOptions, 25, "+91 8321098765"));

        hostels.add(new Hostel("H018", "Foodie's Hostel", "FC Road, Pune", 4.6f, 187,
                "₹9,000 onwards",
                List.of("https://example.com/hostel18/img1.jpg"),
                new ArrayList<>(Arrays.asList(Amenity.WIFI, Amenity.FOOD, Amenity.LAUNDRY, Amenity.STUDY_ROOM)),
                "Best hostel for food lovers with home-style meals", commonPolicies,
                sharedRoomOptions, 16, "foodieshostel@example.com"));

        hostels.add(new Hostel("H019", "Sports Hostel Pune", "Balewadi, Pune", 4.3f, 112,
                "₹8,000 onwards",
                List.of("https://example.com/hostel19/img1.jpg"),
                new ArrayList<>(Arrays.asList(Amenity.WIFI, Amenity.GYM, Amenity.PARKING)),
                "Hostel with sports facilities and gym", commonPolicies,
                sharedRoomOptions, 18, "sports@hostelpune.com"));

        hostels.add(new Hostel("H020", "Zen Stay Pune", "Wakad, Pune", 4.1f, 93,
                "₹11,000 onwards",
                List.of("https://example.com/hostel20/img1.jpg"),
                premiumAmenities, "Peaceful stay with meditation area", commonPolicies,
                privateRoomOptions, 7, "zenstay@pune.com"));

        return hostels;
    }

    public static List<FoodItem> getFoodItems() {
        return Arrays.asList(
                new FoodItem("1", "Rajma Chawal", "Steamed rice with kidney beans curry", 50.0, "", 20, 4.2f, 80, false, "Lunch", true),
                new FoodItem("2", "Aloo Paratha", "Stuffed potato flatbread with butter", 35.0, "", 15, 4.5f, 100, false, "Breakfast", true),
                new FoodItem("3", "Masala Dosa", "Crispy dosa filled with spicy potato mix", 40.0, "", 20, 4.3f, 90, false, "Breakfast", true),
                new FoodItem("4", "Sambhar Rice", "Steamed rice with sambhar", 45.0, "", 15, 4.0f, 75, false, "Lunch", true),
                new FoodItem("5", "Paneer Butter Masala", "Creamy tomato curry with cottage cheese", 70.0, "", 25, 4.6f, 120, true, "Dinner", true),
                new FoodItem("6", "Chole Bhature", "Spicy chickpeas served with fried bread", 50.0, "", 20, 4.4f, 95, false, "Lunch", true),
                new FoodItem("7", "Veg Pulao", "Aromatic rice with mixed vegetables", 40.0, "", 15, 4.1f, 60, false, "Lunch", true),
                new FoodItem("8", "Idli Sambhar", "Steamed rice cakes served with sambhar", 30.0, "", 10, 4.2f, 85, false, "Breakfast", true),
                new FoodItem("9", "Poha", "Flattened rice cooked with turmeric and peas", 25.0, "", 10, 4.0f, 65, false, "Breakfast", true),
                new FoodItem("10", "Dal Tadka", "Yellow lentils tempered with spices", 30.0, "", 15, 4.3f, 88, false, "Dinner", true),
                new FoodItem("11", "Curd Rice", "Curd mixed with rice and tempered spices", 30.0, "", 10, 4.1f, 70, false, "Lunch", true),
                new FoodItem("12", "Maggi Noodles", "Quick and easy instant noodles", 25.0, "", 7, 3.8f, 110, false, "Snack", true),
                new FoodItem("13", "Chapati Sabzi", "Whole wheat roti with seasonal vegetable", 35.0, "", 10, 4.0f, 60, false, "Dinner", true),
                new FoodItem("14", "Bread Pakora", "Fried bread stuffed with potato filling", 20.0, "", 10, 3.9f, 55, false, "Snack", true),
                new FoodItem("15", "Pav Bhaji", "Spicy mashed vegetables with bread rolls", 45.0, "", 15, 4.4f, 95, true, "Snack", true),
                new FoodItem("16", "Upma", "Semolina cooked with vegetables", 30.0, "", 10, 4.0f, 58, false, "Breakfast", true),
                new FoodItem("17", "Kadhi Chawal", "Curd-based curry with steamed rice", 40.0, "", 15, 4.1f, 70, false, "Lunch", true),
                new FoodItem("18", "Lassi", "Sweet or salty yogurt-based drink", 20.0, "", 5, 4.5f, 90, false, "Beverage", true),
                new FoodItem("19", "Filter Coffee", "Strong South Indian filter coffee", 15.0, "", 5, 4.7f, 85, false, "Beverage", true),
                new FoodItem("20", "Tea", "Hot masala chai", 10.0, "", 5, 4.6f, 100, true, "Beverage", true)
        );
    }

    public static List<Ticket> getTicketSampleData() {
        List<Ticket> ticketList = new ArrayList<>();

        // Ticket 1
        ticketList.add(new Ticket(
                "Water leakage in bathroom",
                "There is water leakage from the shower in my bathroom. It's causing damage to the floor and creating slipping hazards.",
                "Maintenance",
                Priority.HIGH,
                "user123",
                "A-101"
        ));

        // Ticket 2
        ticketList.add(new Ticket(
                "Wi-Fi connectivity issues",
                "I'm experiencing frequent disconnections and slow internet speed in my room for the past two days.",
                "Network",
                Priority.MEDIUM,
                "user456",
                "B-205"
        ));

        // Ticket 3
        ticketList.add(new Ticket(
                "Broken ceiling fan",
                "The ceiling fan in my room makes loud noise and sometimes stops working completely.",
                "Electrical",
                Priority.MEDIUM,
                "user789",
                "C-304"
        ));

        // Ticket 4
        ticketList.add(new Ticket(
                "Need extra blanket",
                "With the temperature dropping, I would like to request an extra blanket for my bed.",
                "Housekeeping",
                Priority.LOW,
                "user234",
                "D-408"
        ));

        // Ticket 5
        ticketList.add(new Ticket(
                "Room lock malfunctioning",
                "My room lock is not working properly. Sometimes it takes multiple attempts to lock or unlock the door.",
                "Security",
                Priority.HIGH,
                "user567",
                "A-203"
        ));

        // Ticket 6
        ticketList.add(new Ticket(
                "Noisy neighbors after hours",
                "The residents in room B-302 play loud music after 11 PM, which is disturbing my sleep and studies.",
                "Complaints",
                Priority.MEDIUM,
                "user890",
                "B-301"
        ));

        // Ticket 7
        ticketList.add(new Ticket(
                "Air conditioning not cooling",
                "The AC in my room isn't cooling properly despite being set to the lowest temperature.",
                "HVAC",
                Priority.MEDIUM,
                "user321",
                "C-105"
        ));

        // Ticket 8
        ticketList.add(new Ticket(
                "Dining area cleanliness",
                "The dining area hasn't been cleaned properly for the past two days. Tables are sticky and there's food waste on the floor.",
                "Cleaning",
                Priority.LOW,
                "user654",
                "D-202"
        ));

        // Ticket 9
        ticketList.add(new Ticket(
                "Pest control needed",
                "I've noticed cockroaches in my room and bathroom. Please schedule pest control as soon as possible.",
                "Maintenance",
                Priority.HIGH,
                "user987",
                "A-308"
        ));

        // Ticket 10
        ticketList.add(new Ticket(
                "Room painting request",
                "The paint in my room is peeling off in several places. Requesting a fresh coat of paint.",
                "Maintenance",
                Priority.LOW,
                "user432",
                "B-407"
        ));

        // Set unique IDs for each ticket
        for (int i = 0; i < ticketList.size(); i++) {
            ticketList.get(i).setId("TKT" + (1000 + i));
        }

        return ticketList;
    }

}