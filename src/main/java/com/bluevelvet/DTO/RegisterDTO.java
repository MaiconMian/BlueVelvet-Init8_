package com.bluevelvet.DTO;

import java.util.List;

public record RegisterDTO (String name, String lastName, String email, String password, List<Integer> Roles){
}
