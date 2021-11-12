package by.bsu.informationhandling.service;

import by.bsu.informationhandling.constant.SymbolType;
import by.bsu.informationhandling.entity.TextComposite;
import by.bsu.informationhandling.exception.ServiceException;

import java.util.EnumSet;
import java.util.List;
import java.util.Map;

public interface TextHandlingService {
    List<TextComposite> sortParagraphsAccordingSentenceCount(TextComposite text) throws ServiceException;

    TextComposite findSentenceWithLongestWord(TextComposite text) throws ServiceException;

    void deleteSentencesFromTextWithWordsLessThanGiven(TextComposite text, int border) throws ServiceException;

    Map<String, Integer> findSameWordsAndTheirCountInText(TextComposite text) throws ServiceException;

    Map<SymbolType, Integer> findSymbolAmountInSentence(TextComposite sentence, EnumSet<SymbolType> searchCriteria) throws ServiceException;

}
