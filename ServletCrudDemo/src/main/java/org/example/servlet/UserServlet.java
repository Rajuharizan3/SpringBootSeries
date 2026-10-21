package org.example.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.entity.User;
import org.example.service.UserService;

import java.io.IOException;
import java.util.List;


@WebServlet("/users")
public class UserServlet extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String number = request.getParameter("number");

        if(id == null || name == null
                || email == null || number == null){
            response.setStatus(400);
            response.setContentType("application/json");
            response.getWriter().write(
                    "{\n" +
                            "    \"message\" : \"Some fields are missing...\"\n" +
                            "}"
            );
        }

        User user = new User(id, name, email, number);
        User createdUser = userService.createUser(user);

        response.setStatus(201);
        response.setContentType("application/json");
        response.getWriter().write(
                "{\n" +
                        "    \"message\" : \"User created successfully\"\n" +
                        "}"
        );
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException{

        String idParam = request.getParameter("id");
        if(idParam == null){
            List<User> allUsers = userService.getAllUser();
            response.setStatus(201);
            response.setContentType("application/json");
            response.getWriter().write(usersToJson(allUsers));
            return;
        }

        Integer id = Integer.parseInt(idParam);
        User userResp = userService.getUserById(id);

        if(userResp == null){
            response.setStatus(404);
            response.setContentType("application/json");
        }

        response.setStatus(201);
        response.setContentType("application/json");
        response.getWriter().write(userToJson(userResp));

    }


    @Override
    public void doDelete(HttpServletRequest request,
                         HttpServletResponse response){
        Integer id = Integer.parseInt(request.getParameter("id"));
        String deleteStudent = userService.deleteById(id);

    }

    @Override
    public void doPut(HttpServletRequest request,
                      HttpServletResponse response){
        Integer id = Integer.parseInt(request.getParameter("id"));
        
    }

    private String userToJson(User user){
        return "{\n" +
                "    \"id\" : " + user.getId() + ",\n" +
                "    \"name\" : " + user.getName() + ",\n" +
                "    \"email\" : " + user.getEmail() + ",\n" +
                "    \"number\" : " +  user.getNumber() + "\n" +
                "}";
    }

    private String usersToJson(List<User> users){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");

        for(int i = 0; i<users.size(); i++){
            stringBuilder.append(userToJson(users.get(i)));

            if(i < users.size() - 1){
                stringBuilder.append(",");
            }
        }

        stringBuilder.append("]");
        return stringBuilder.toString();
    }


}
