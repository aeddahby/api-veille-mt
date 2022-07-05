package ma.iam.wissal.veille.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import ma.iam.wissal.veille.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EntityMDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityMDTO.class);
        EntityMDTO entityMDTO1 = new EntityMDTO();
        entityMDTO1.setId(1L);
        EntityMDTO entityMDTO2 = new EntityMDTO();
        assertThat(entityMDTO1).isNotEqualTo(entityMDTO2);
        entityMDTO2.setId(entityMDTO1.getId());
        assertThat(entityMDTO1).isEqualTo(entityMDTO2);
        entityMDTO2.setId(2L);
        assertThat(entityMDTO1).isNotEqualTo(entityMDTO2);
        entityMDTO1.setId(null);
        assertThat(entityMDTO1).isNotEqualTo(entityMDTO2);
    }
}
