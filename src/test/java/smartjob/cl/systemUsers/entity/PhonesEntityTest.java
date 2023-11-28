package smartjob.cl.systemUsers.entity;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PhonesEntityTest {

    @Test
    public void testPhonesEntityProperties() {
        PhonesEntity phonesEntity = PhonesEntity.builder()
                .id(1L)
                .number("123456789")
                .cityCode("123")
                .countryCode("1")
                .build();

        assertThat(phonesEntity.getId()).isEqualTo(1L);
        assertThat(phonesEntity.getNumber()).isEqualTo("123456789");
        assertThat(phonesEntity.getCityCode()).isEqualTo("123");
        assertThat(phonesEntity.getCountryCode()).isEqualTo("1");
    }

    @Test
    public void testPhonesEntityEquality() {
        PhonesEntity phonesEntity1 = PhonesEntity.builder()
                .id(1L)
                .number("123456789")
                .cityCode("123")
                .countryCode("1")
                .build();

        PhonesEntity phonesEntity2 = PhonesEntity.builder()
                .id(1L)
                .number("123456789")
                .cityCode("123")
                .countryCode("1")
                .build();

        assertThat(phonesEntity1).isNotEqualTo(phonesEntity2);
        assertThat(phonesEntity1.hashCode()).isNotEqualTo(phonesEntity2.hashCode());
    }

    @Test
    public void testPhonesEntityToString() {
        PhonesEntity phonesEntity = PhonesEntity.builder()
                .id(1L)
                .number("123456789")
                .cityCode("123")
                .countryCode("1")
                .build();

        assertThat(phonesEntity.toString()).doesNotContain("id=1", "number=123456789", "cityCode=123", "countryCode=1");
    }
}
