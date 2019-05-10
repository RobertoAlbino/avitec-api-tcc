package com.roberto.avitec.business;

import com.roberto.avitec.domain.base.RetornoBaseModel;
import com.roberto.avitec.domain.entities.Indicador;
import com.roberto.avitec.domain.entities.TabelaPadrao;
import com.roberto.avitec.domain.models.IndicadorModel;
import com.roberto.avitec.service.FirebaseService;
import com.roberto.avitec.service.IndicadorService;
import com.roberto.avitec.utils.DateUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Transactional
public class IndicadorBusiness {

    private LoteBusiness loteBusiness;
    private TabelaPadraoBusiness tabelaPadraoBusiness;
    private IndicadorService indicadorService;
    private FirebaseService firebaseService;

    @Autowired
    public IndicadorBusiness(IndicadorService indicadorService,
                             LoteBusiness loteBusiness,
                             TabelaPadraoBusiness tabelaPadraoBusiness,
                             FirebaseService firebaseService) {
        this.indicadorService = indicadorService;
        this.loteBusiness = loteBusiness;
        this.tabelaPadraoBusiness = tabelaPadraoBusiness;
        this.firebaseService = firebaseService;
    }

    private Indicador toEntity(IndicadorModel model)  {
        Indicador indicador = new Indicador();
        indicador.setZona(model.getZona());
        indicador.setTemperatura(model.getTemperatura());
        indicador.setUmidade(model.getUmidade());
        indicador.setData(DateUtils.now());
        indicador.setLote(loteBusiness.getLoteAtivo());
        return indicador;
    }

    private boolean compareTemperaturaValue(TabelaPadrao tabelaPadrao, Indicador indicador) {
        return  indicador.getTemperatura().compareTo(tabelaPadrao.getTemperaturaMinima()) == -1 ||
                indicador.getTemperatura().compareTo(tabelaPadrao.getTemperaturaMaxima()) == 1;
    }

    private boolean compareUmidadadeValue(TabelaPadrao tabelaPadrao, Indicador indicador) {
        return  indicador.getUmidade().compareTo(tabelaPadrao.getUmidadeMinima()) == -1 ||
                indicador.getUmidade().compareTo(tabelaPadrao.getUmidadeMaxima()) == 1;
    }

    private Indicador setIndicadorHealth(Indicador indicador, Integer semana) {
        List<TabelaPadrao> listaTabelaPadrao = (List<TabelaPadrao>) tabelaPadraoBusiness.findAll().getObjeto();
        TabelaPadrao tabelaPadraoSetimoDias = listaTabelaPadrao.get(semana);
        if (listaTabelaPadrao == null) {
            throw new RuntimeException("A tabela padrão precisa ser configurada");
        }
        if (compareTemperaturaValue(tabelaPadraoSetimoDias, indicador)) {
            indicador.setTemperaturaIdeal(false);
        } else {
            indicador.setTemperaturaIdeal(true);
        }
        if (compareUmidadadeValue(tabelaPadraoSetimoDias, indicador)) {
            indicador.setUmidadeIdeal(false);
        } else {
            indicador.setUmidadeIdeal(true);
        }
        return indicador;
    }

    private Indicador analyzeIndicador(Indicador indicador) {
        Integer quantidadeDiasLote = loteBusiness.getIdadeLoteDias();
        if (quantidadeDiasLote <= 7) {
            return setIndicadorHealth(indicador, 0);
        } else if (quantidadeDiasLote <= 14) {
            return setIndicadorHealth(indicador, 1);
        } else if (quantidadeDiasLote <= 21) {
            return setIndicadorHealth(indicador, 2);
        } else if (quantidadeDiasLote <= 28) {
            return setIndicadorHealth(indicador, 3);
        } else if (quantidadeDiasLote <= 35) {
            return setIndicadorHealth(indicador, 4);
        } else if (quantidadeDiasLote <= 42) {
            return setIndicadorHealth(indicador, 5);
        } else if (quantidadeDiasLote <= 49) {
            return setIndicadorHealth(indicador, 6);
        } else {
            throw new RuntimeException("A quantidade de dias do lote está fora do padrão configurado");
        }
    }

    public RetornoBaseModel findAll() {
        try {
            JSONObject body = new JSONObject();
            body.put("to", firebaseService.getToken());
            body.put("priority", "high");

            JSONObject notification = new JSONObject();
            notification.put("title", "JSA Notification");
            notification.put("body", "Happy Message!");

            JSONObject data = new JSONObject();
            data.put("Key-1", "JSA Data 1");
            data.put("Key-2", "JSA Data 2");

            body.put("notification", notification);
            body.put("data", data);


            HttpEntity<String> request = new HttpEntity<>(body.toString());

            CompletableFuture<String> pushNotification = firebaseService.send(request);
            CompletableFuture.allOf(pushNotification).join();
            String firebaseResponse = pushNotification.get();
            return new RetornoBaseModel(true, "Lista de indicadores", indicadorService.findAll());
        } catch(Exception ex) {
            return new RetornoBaseModel(false, ex.getMessage(), null);
        }
    }

    public RetornoBaseModel getUltimos() {
        try {
            return new RetornoBaseModel(true, "Lista dos ultímos indicadores", indicadorService.getUltimos());
        } catch(Exception ex) {
            return new RetornoBaseModel(false, ex.getMessage(), null);
        }
    }

    public RetornoBaseModel getByZona(Integer zona) {
        try {
            return new RetornoBaseModel(true, "Lista de indicadores por zona", indicadorService.getByZona(zona));
        } catch(Exception ex) {
            return new RetornoBaseModel(false, ex.getMessage(), null);
        }
    }

    public RetornoBaseModel create(IndicadorModel model) {
        try {
            Indicador indicador = analyzeIndicador(toEntity(model));
            return new RetornoBaseModel(true, "Indicador criado com sucesso", indicadorService.create(indicador));
        } catch(Exception ex) {
            return new RetornoBaseModel(false, ex.getMessage(), null);
        }
    }

    public RetornoBaseModel createList(List<IndicadorModel> models) {
        try {
            List<Indicador> indicadoresCriados = new ArrayList<>();
            for (IndicadorModel model: models) {
                Indicador indicador = analyzeIndicador(toEntity(model));
                indicadoresCriados.add(indicadorService.create(indicador));
            }
            return new RetornoBaseModel(true, "Indicadores criados com sucesso", indicadoresCriados);
        } catch(Exception ex) {
            return new RetornoBaseModel(false, ex.getMessage(), null);
        }
    }

    public RetornoBaseModel delete(Long id) {
        try {
            indicadorService.delete(id);
            return new RetornoBaseModel(true, "Indicador excluído com sucesso", null);
        } catch(Exception ex) {
            return new RetornoBaseModel(false, ex.getMessage(), null);
        }
    }

    public RetornoBaseModel deleteAll() {
        try {
            List<Indicador> indicadores = indicadorService.findAll();
            for (Indicador indicador: indicadores) {
                delete(indicador.getId());
            }
            return new RetornoBaseModel(true, "Todos os indicadores foram excluídos com sucesso", null);
        } catch(Exception ex) {
            return new RetornoBaseModel(false, ex.getMessage(), null);
        }
    }
}
