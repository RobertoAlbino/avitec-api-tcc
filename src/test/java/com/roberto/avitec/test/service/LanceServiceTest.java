package com.roberto.avitec.test.service;

import com.roberto.avitec.domain.models.NovoLanceModel;

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
