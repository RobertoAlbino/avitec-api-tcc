package com.roberto.cotaeasy.test.service;

import com.roberto.cotaeasy.domain.models.NovoLanceModel;

import org.junit.Before;
import org.junit.Test;

public class LanceServiceTest {
    private NovoLanceModel lance;

    @Before
    public void montarLance() {
    }

    @Test
    public void garantirPermitidoApenasUmLanceProduto() {
        NovoLanceModel lance = new NovoLanceModel();
        lance.setLance(1000);
    }
}
