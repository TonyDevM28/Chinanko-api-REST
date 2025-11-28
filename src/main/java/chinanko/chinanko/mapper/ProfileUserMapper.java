package chinanko.chinanko.mapper;

import org.springframework.stereotype.Component;

import chinanko.chinanko.dto.ProfileUserRequest;
import chinanko.chinanko.dto.ProfileUserResponse;
import chinanko.chinanko.model.ProfileUser;
import chinanko.chinanko.model.Town;
import chinanko.chinanko.model.User;

@Component
public class ProfileUserMapper {
   public static ProfileUserResponse toResponse(ProfileUser p){
    if(p == null) return null;

    // Usamos variables auxiliares seguras
    String userName = (p.getUser() != null) ? p.getUser().getNameUser() : "Desconocido";
    String townName = (p.getTown() != null) ? p.getTown().getNameTown() : "Sin Ciudad";
    
    // Nota: Si realmente necesitas usar los Mappers de User y Town porque tienen lógica 
    // especial de formateo, mantén tu lógica anterior pero agrega la validación null 
    // en el builder como te mostré en el punto 1.

    return ProfileUserResponse.builder()
        .firstName(p.getFirstName())
        .lastName(p.getLastName())
        .bornDate(p.getBornDate())
        .user(userName) // Ahora es seguro
        .town(townName) // Ahora es seguro
        .build();
}

    public static ProfileUser toEntity(ProfileUserRequest p){
        if(p==null) return null;
        ProfileUser r = new ProfileUser();
        r.setIdProfileUser(r.getIdProfileUser());
        r.setFirstName(p.getFirstName());
        r.setLastName(p.getLastName());
        r.setBornDate(p.getBornDate());
        r.setUser(User.builder().idUser(p.getUserId()).build());
        r.setTown(Town.builder().idTown(p.getTownId()).build());
        return r;
    }

    public static void copyToEntity(ProfileUser p, ProfileUserRequest r){
        if(p==null || r==null) return;
        p.setFirstName(r.getFirstName());
        p.setLastName(r.getLastName());
        p.setBornDate(r.getBornDate());
        p.setUser(User.builder().idUser(r.getUserId()).build());
        p.setTown(Town.builder().idTown(r.getTownId()).build());
        
    }
}
