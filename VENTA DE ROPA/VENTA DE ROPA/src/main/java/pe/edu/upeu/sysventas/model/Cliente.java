package pe.edu.upeu.sysventas.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.upeu.sysventas.enums.TipoDocumento;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    private Long idCliente;

    @NotBlank(message = "El DNI/RUC es obligatorio")
    private String dniruc;

    @NotBlank(message = "El nombre o razón social es obligatorio")
    private String nombres;

    private String repLegal;

    @NotNull(message = "El tipo de documento es obligatorio")
    private TipoDocumento tipoDocumento;
}