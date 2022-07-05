package ma.iam.wissal.veille.domain;

import static org.assertj.core.api.Assertions.assertThat;

import ma.iam.wissal.veille.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EntityMTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityM.class);
        EntityM entityM1 = new EntityM();
        entityM1.setId(1L);
        EntityM entityM2 = new EntityM();
        entityM2.setId(entityM1.getId());
        assertThat(entityM1).isEqualTo(entityM2);
        entityM2.setId(2L);
        assertThat(entityM1).isNotEqualTo(entityM2);
        entityM1.setId(null);
        assertThat(entityM1).isNotEqualTo(entityM2);
    }
}
