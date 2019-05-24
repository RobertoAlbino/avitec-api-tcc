package com.roberto.avitec.business;

import com.roberto.avitec.domain.base.RetornoBaseModel;
import com.roberto.avitec.domain.entities.Indicador;
import com.roberto.avitec.domain.entities.TabelaPadrao;
import com.roberto.avitec.domain.enums.TipoEnvioPush;
import com.roberto.avitec.domain.models.ExtremosIndicadoresPorDiaModel;
import com.roberto.avitec.domain.models.IndicadorModel;
import com.roberto.avitec.domain.models.MediaUltimosIndicadoresModel;
import com.roberto.avitec.service.FirebaseService;
import com.roberto.avitec.service.IndicadorService;
import com.roberto.avitec.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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

    private void validateNecessarioNotificarUsuarioTemperatura(TabelaPadrao tabelaPadrao, Indicador indicador) {
        if (indicador.getTemperatura().min(tabelaPadrao.getTemperaturaMaxima()).compareTo(new BigDecimal(0)) == 1) {
            firebaseService.validateEnvioPermitidoAndSendPush("Anomalia na temperatura", "Temperatura atual: " + indicador.getTemperatura(), TipoEnvioPush.TEMPERATURA);
        }
        if (indicador.getTemperatura().min(tabelaPadrao.getTemperaturaMinima()).compareTo(new BigDecimal(0)) == -1) {
            firebaseService.validateEnvioPermitidoAndSendPush("Anomalia na temperatura", "Temperatura atual: " + indicador.getTemperatura(), TipoEnvioPush.TEMPERATURA);
        }
    }

    private void validateNecessarioNotificarUsuarioUmidade(TabelaPadrao tabelaPadrao, Indicador indicador) {
        if (indicador.getUmidade().min(tabelaPadrao.getUmidadeMaxima()).compareTo(new BigDecimal(0)) == 1) {
            firebaseService.validateEnvioPermitidoAndSendPush("Anomalia na umidade", "Umidade atual: " + indicador.getUmidade(), TipoEnvioPush.UMIDADE);
        }
        if (indicador.getUmidade().min(tabelaPadrao.getUmidadeMinima()).compareTo(new BigDecimal(0)) == -1) {
            firebaseService.validateEnvioPermitidoAndSendPush("Anomalia na umidade", "Umidade atual: " + indicador.getUmidade(), TipoEnvioPush.UMIDADE);
        }
    }

    private Indicador setIndicadorHealth(Indicador indicador, Integer semana) {
        List<TabelaPadrao> listaTabelaPadrao = (List<TabelaPadrao>) tabelaPadraoBusiness.findAll().getObjeto();
        TabelaPadrao tabelaPadraoSetimoDias = listaTabelaPadrao.get(semana);
        if (listaTabelaPadrao == null) {
            throw new RuntimeException("A tabela padrão precisa ser configurada");
        }
        if (compareTemperaturaValue(tabelaPadraoSetimoDias, indicador)) {
            indicador.setTemperaturaIdeal(false);
            validateNecessarioNotificarUsuarioTemperatura(tabelaPadraoSetimoDias, indicador);
        } else {
            indicador.setTemperaturaIdeal(true);
        }
        if (compareUmidadadeValue(tabelaPadraoSetimoDias, indicador)) {
            indicador.setUmidadeIdeal(false);
            validateNecessarioNotificarUsuarioUmidade(tabelaPadraoSetimoDias, indicador);
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

    public RetornoBaseModel mediaUltimosIndicadores() {
        try {
            MediaUltimosIndicadoresModel model = new MediaUltimosIndicadoresModel();
            List<Indicador> indicadores = indicadorService.getUltimos();
            BigDecimal mediaTemperatura = indicadores.stream().map(Indicador::getTemperatura).reduce(BigDecimal.ZERO, (a, b) -> a.add(b)).divide(new BigDecimal(indicadores.size()), BigDecimal.ROUND_UP);
            BigDecimal mediaUmidade = indicadores.stream().map(Indicador::getUmidade).reduce(BigDecimal.ZERO, (a, b) -> a.add(b)).divide(new BigDecimal(indicadores.size()), BigDecimal.ROUND_UP);
            Indicador indicador = new Indicador();
            indicador.setTemperatura(mediaTemperatura);
            indicador.setUmidade(mediaUmidade);
            indicador = analyzeIndicador(indicador);
            model.setMediaTemperatura(mediaTemperatura);
            model.setMediaUmidade(mediaUmidade);
            model.setTemperaturaIdeal(indicador.isTemperaturaIdeal());
            model.setUmidadeIdeal(indicador.isUmidadeIdeal());
            return new RetornoBaseModel(true, "Média últimos indicadores", model);
        } catch(Exception ex) {
            return new RetornoBaseModel(false, ex.getMessage(), null);
        }
    }

    public RetornoBaseModel informacoesFechamentoLote(Long idLote) {
        try {
            List<ExtremosIndicadoresPorDiaModel> extremosIndicadoresPorDia = new LinkedList<>();
            Object[] indicadores = indicadorService.informacoesFechamentoLote(idLote);
            for (Object indicador : indicadores) {
                ExtremosIndicadoresPorDiaModel model = new ExtremosIndicadoresPorDiaModel();
                model.setData((Date) ((Object[]) indicador)[0]);
                model.setTemperaturaMaxima((BigDecimal) ((Object[]) indicador)[1]);
                model.setUmidadeMaxima((BigDecimal) ((Object[]) indicador)[2]);
                model.setTemperaturaMinima((BigDecimal) ((Object[]) indicador)[3]);
                model.setUmidadeMinima((BigDecimal) ((Object[]) indicador)[4]);
                extremosIndicadoresPorDia.add(model);
            }
            return new RetornoBaseModel(true, "Informações de fechamento do lote", extremosIndicadoresPorDia);
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
