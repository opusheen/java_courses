package ru.stqa.pft.githubsample;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GitHubTests {
    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub("4404035eb82862c88b2f27d4c45af63c5eba94b5");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("opusheen", "java_courses")).commits();
        for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
            System.out.println(new RepoCommit.Smart(commit).message());
        }

        }
    }

