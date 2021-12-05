package com.zefrocan.ipldashboard.batch.config;

import com.zefrocan.ipldashboard.batch.model.MatchFeedData;
import com.zefrocan.ipldashboard.model.Match;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.persistence.EntityManagerFactory;

@Configuration
@EnableBatchProcessing
public class MatchFeedDataBatchJobConfig {

    private static final Logger LOG = LoggerFactory.getLogger(MatchFeedDataBatchJobConfig.class);
    private static final String[] COLUMN_NAMES = new String[]{"id", "city", "date", "player_of_match", "venue", "neutral_venue", "team1", "team2", "toss_winner", "toss_decision", "winner", "result", "result_margin", "eliminator", "method", "umpire1", "umpire2",};

    @Bean
    public Job createMatchFeedDataLoadJob(JobBuilderFactory jobBuilderFactory,
                                          StepBuilderFactory stepBuilderFactory,
                                          ItemReader<MatchFeedData> itemReader,
                                          ItemProcessor<MatchFeedData, Match> itemProcessor,
                                          ItemWriter<Match> itemWriter,
                                          MatchFeedDataBatchNotificationListener matchFeedDataBatchNotificationListener) {
        // creating spring batch job step below.
        final Step matchFeedDataLoadStep = stepBuilderFactory.get("match-feed-data-step")
                .<MatchFeedData, Match>chunk(10)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();

        // creating and returning the job configured with spring batch, with the step created above.
        return jobBuilderFactory.get("match-feed-data-job")
                .incrementer(new RunIdIncrementer())
                .listener(matchFeedDataBatchNotificationListener)
                // we can use .flow() in case we have more than one
                // step for our ETL job. eg: .flow(step1).next(step2).next(step3) and so on.
                // for this particular use case, we have only one step, that is why we used .start(step)
                .start(matchFeedDataLoadStep)
                .build();
    }

    @Bean
    public ItemReader<MatchFeedData> createFileItemReader() {
        return new FlatFileItemReaderBuilder<MatchFeedData>()
                .name("match-feed-data-reader")
                .resource(new ClassPathResource("kaggle-match-data-2008-2020.csv"))
                .strict(false)
                .linesToSkip(1)
                .delimited()
                .names(COLUMN_NAMES)
                .fieldSetMapper(new BeanWrapperFieldSetMapper<MatchFeedData>() {{
                    setTargetType(MatchFeedData.class);
                }})
                .build();
    }

    @Bean
    public ItemWriter<Match> createItemWriter(EntityManagerFactory entityManagerFactory) {
        return new JpaItemWriterBuilder<Match>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }
}
